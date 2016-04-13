package controllers

import com.google.inject.{Inject, Singleton}
import controllers.actions.MongoCrud
import models.commons.{Helpers, MongoCollectionNames}
import models.commons.CollectionFields._
import models.utils.Errors
import models.{SignIn, SignUp, User}
import org.mindrot.jbcrypt.BCrypt
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.{JsError, JsObject, JsSuccess, Json}
import play.api.mvc.Action
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.play.json.collection.JSONCollection
import play.modules.reactivemongo.json._
import reactivemongo.api.ReadPreference
import reactivemongo.api.commands.WriteResult

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

/**
  * Created by stephane on 04/04/2016.
  */
@Singleton
class UserController @Inject()(override val reactiveMongoApi: ReactiveMongoApi)(implicit exec: ExecutionContext)
  extends CommonController(reactiveMongoApi) with MongoCrud[User] {

  implicit lazy val users: Future[JSONCollection] = getJSONCollection(MongoCollectionNames.Users)

  // for password
  private final val salt = BCrypt.gensalt()

  override protected def create(elt: User): Future[WriteResult] = users.flatMap(_.insert[User](elt))

  def signIn = Action.async(parse.json) { request =>
    SignIn.signInForm.bind(request.body).fold(
      hasErrors => getJsonFormErrorResult[SignIn](hasErrors),
      signIn => {
        val wr = for {
          validMail <- checkFieldExist(Email, signIn.email)
          user <- getUserFromUniqueField(Email, signIn.email)
        } yield {
          val password = Try(user.get.password).getOrElse("")
          val validPass = Try(BCrypt.checkpw(signIn.password, password)).getOrElse(false)
          if (validMail && validPass) user else None
        }
        wr.map {
          OkOrNot[User](_)(getJsonResult(_).withSession(), BadRequest(Json.toJson(signIn)))
        }
      }
    )
  }

  protected def getUserFromUniqueField(fieldName: String, fieldInput: String) = {
    users.flatMap { collection =>
      collection.find(Json.obj(fieldName -> fieldInput)).cursor[User]().collect[List]().map { users =>
        if (users != null && users != Nil && users.size == 1) users.headOption else None
      }
    }
  }

  protected def checkSignUpData(nickName: String, email: String): Future[Boolean] = {
    val jsObject = Json.obj("$or" -> Json.arr(Json.obj(NickName -> nickName), Json.obj(Email -> email)))
    checkFieldExist(jsObject)
  }

  def signUp = Action.async(parse.json) { request =>
    SignUp.signUpForm.bind(request.body).fold(
      hasErrors => getJsonFormErrorResult[SignUp](hasErrors),
      signUp => {
        val wr = for {
          check <- checkSignUpData(signUp.nickName, signUp.email)
        } yield {
          if (!check) {
            val id = Helpers.generateBsonId
            val user = User(Some(id), signUp.name, signUp.firstName, signUp.nickName, signUp.email, BCrypt.hashpw(signUp.password, salt))
            create(user)
            Some(user)
          }
          else None
        }
        wr.map(OkOrNot[User](_)(getJsonResult(_).withSession(), Conflict(Json.toJson(signUp))))
      }
    )
  }

  def getUserFromNickName(nickName: String) = Action.async { request =>
    request.session.get(Id).map { id =>
      users.flatMap { collection =>
        collection.find(Json.obj(NickName -> nickName)).cursor[User](ReadPreference.Primary).collect[List]().map { list =>
          // nickname is unique so we get the first element
          val user = list.headOption
          OkOrNot[User](user)(getJsonResult(_), BadRequest("There are no user with this nickname"))
        }
      }
    }.getOrElse(Future.successful(Unauthorized("You are not connected")))
  }

}
