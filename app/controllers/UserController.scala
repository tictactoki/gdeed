package controllers

import com.google.inject.{Inject, Singleton}
import models.commons.MongoCollectionNames
import models.{SignIn, User}
import org.mindrot.jbcrypt.BCrypt
import play.api.libs.json.{JsError, JsSuccess, Json}
import play.api.mvc.Action
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by stephane on 04/04/2016.
  */
@Singleton
class UserController @Inject ()(override val reactiveMongoApi: ReactiveMongoApi)(implicit exec: ExecutionContext)
  extends CommonController(reactiveMongoApi) {

  lazy val users: Future[JSONCollection]= getJSONCollection(MongoCollectionNames.Users)

  // for password
  private final val salt = BCrypt.gensalt()

  def signIn = Action.async(parse.json) { implicit request =>
    Json.fromJson[SignIn](request.body) match {
      case JsSuccess(signIn, path) => {

        ???
      }
      case JsError(error) => ???
    }
  }

  def signUp = Action.async(parse.json) { implicit request =>
    ???
  }


  protected def userExist(id: String): Future[Boolean] = {
    ???
  }

  protected def getUserFromId(id: String): Future[User] = {
    ???
  }

}
