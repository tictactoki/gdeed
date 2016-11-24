package controllers

import com.google.inject.{Inject, Singleton}
import controllers.actions.MongoCrud
import controllers.interfaces.UserControllerInterface
import models.commons.{Helpers, MongoCollectionNames}
import models.commons.CollectionFields._
import models.{SignIn, User}
import org.mindrot.jbcrypt.BCrypt
import play.api.Configuration
import play.api.data.Form
import play.api.libs.json.Json
import play.api.mvc.{Session, Action}
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
class UserController @Inject()(override val reactiveMongoApi: ReactiveMongoApi, override implicit val config: Configuration, override implicit val webJarAssets: WebJarAssets)(implicit exec: ExecutionContext)
  extends CommonController(reactiveMongoApi, config, webJarAssets) with MongoCrud[User] with UserControllerInterface {

  implicit override lazy val mainCollection: Future[JSONCollection] = getJSONCollection(MongoCollectionNames.Users)

  // for password
  private final val salt = BCrypt.gensalt()

  override protected def insert(elt: User): Future[WriteResult] = mainCollection.flatMap(_.insert[User](elt))

  def index = Action { Ok(views.html.login(None)) }

  def signIn = Action.async { implicit request =>
    SignIn.signInForm.bindFromRequest().fold(
      hasErrors => Future.successful(BadRequest(views.html.login(Option(getSeqFromError(hasErrors))))),
        //getJsonFormErrorResult[SignIn](hasErrors),
      signIn => {
        val wr = for {
          validMail <- checkFieldExist(Email, signIn.email)
          user <- getUserFromUniqueField(Email, signIn.email)
        } yield {
          val password = Try(user.get.password).getOrElse("")
          val validPass = Try(BCrypt.checkpw(signIn.password, password)).getOrElse(false)
          if (validMail && validPass) user else None
        }
        wr.map { u =>
          OkOrNot[User](u)(getJsonResult(_).withSession(createSession(u)), BadRequest(Json.toJson(signIn.copy(password = ""))))
        }
      }
    )
  }

  def signUp = Action.async { implicit request =>
    User.userForm.bindFromRequest().fold(
      hasErrors => getJsonFormErrorResult[User](hasErrors),
      signUp => {
        val wr = for {
          check <- checkSignUpData(signUp.nickName, signUp.email)
        } yield {
          if (!check) {
            val id = Helpers.generateBsonId
            val user = User(Some(id), signUp.name, signUp.firstName, signUp.nickName, signUp.email, BCrypt.hashpw(signUp.password, salt))
            insert(user)
            Some(user)
          }
          else None
        }
        wr.map(OkOrNot[User](_)(getJsonResult(_).withSession(), Conflict(Json.toJson(signUp.copy(password = "")))))
      }
    )
  }

  def getUsers = Action.async { request =>
    for {
      collection <- users
      list <- collection.find(Json.obj()).cursor[User]().collect[List]()
    } yield {
      Ok(Json.toJson(list))
    }
    /*users.flatMap{ collection =>
      collection.find(Json.obj()).cursor[User]().collect[List]().map { list =>
        Ok(Json.toJson(list))
      }
    }*/
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

  protected def createSession(user: Option[User]) = {
    Session(user.map { user =>
      List(Id -> user._id.getOrElse(throw new Exception("error in session")), NickName -> user.nickName, Email -> user.email).toMap
    }.getOrElse(throw new Exception("User doesn't exist")))
  }

  protected def getUserFromUniqueField(fieldName: String, fieldInput: String) = {
    mainCollection.flatMap { collection =>
      collection.find(Json.obj(fieldName -> fieldInput)).cursor[User]().collect[List]().map { users =>
        if (users != null && users != Nil && users.size == 1) users.headOption else None
      }
    }
  }

  protected def checkSignUpData(nickName: String, email: String): Future[Boolean] = {
    val jsObject = Json.obj("$or" -> Json.arr(Json.obj(NickName -> nickName), Json.obj(Email -> email)))
    checkFieldExist(jsObject)
  }

}
