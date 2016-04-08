package controllers

import com.google.inject.{Inject, Singleton}
import models.commons.{Helpers, MongoCollectionNames}
import models.commons.CollectionFields._
import models.{MongoCrud, SignIn, SignUp, User}
import org.mindrot.jbcrypt.BCrypt
import play.api.libs.json.{JsError, JsSuccess, Json}
import play.api.mvc.Action
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.play.json.collection.JSONCollection
import play.modules.reactivemongo.json._
import reactivemongo.api.commands.WriteResult

import scala.concurrent.{ExecutionContext, Future}

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
    Json.fromJson[SignIn](request.body) match {
      case JsSuccess(signIn, path) => {
        val wr = for {
          validMail <- checkFieldExist(Email, signIn.email)
          user <- getUserFromUniqueField(Email, signIn.email)
        } yield {
          //TODO: handle a better exception
          val validPass = BCrypt.checkpw(signIn.password, user.getOrElse(throw new Exception("User doesn't exist")).password)
          if (validMail && validPass) user else None
        }
        wr.map { opt => opt match {
          case Some(user) => Ok("ok")
          case None => Ok("ok")
          }
        }
      }
      case JsError(error) => ???
    }
  }

  protected def getUserFromUniqueField(fieldName: String, fieldInput: String) = {
    users.flatMap { collection =>
      collection.find(Json.obj(fieldName -> fieldInput)).cursor[User]().collect[List]().map { users =>
        if (users != null && users != Nil && users.size == 1) users.headOption else None
      }
    }
  }


  def checkSignUpData = Action.async(parse.json) { request =>
    ???
  }

  def signUp = Action.async(parse.json) { request =>
    Json.fromJson[SignUp](request.body) match {
      case JsSuccess(signUp, path) =>
          val id = Helpers.generateId
          val user = User(Some(id), signUp.name, signUp.firstName, signUp.nickName, signUp.email, BCrypt.hashpw(signUp.password, salt))
          create(user).map(_ => Ok("ok"))
      case JsError(errors) => ???
    }
  }

  protected def getUserFromId(id: String): Future[User] = {
    ???
  }

}
