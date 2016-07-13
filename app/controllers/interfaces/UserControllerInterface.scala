package controllers.interfaces

import controllers.UserController
import models.User
import models.commons.CollectionFields._
import play.api.libs.json.Json
import play.api.mvc.Session

import scala.concurrent.Future

/**
  * Created by stephane on 13/07/2016.
  */
trait UserControllerInterface { self: UserController =>


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
