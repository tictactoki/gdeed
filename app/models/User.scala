package models

import play.api.libs.json.Json
import commons.CollectionFields._
/**
  * Created by stephane on 04/04/2016.
  */
case class SignIn(email: String, password: String)
case class SignUp(name: String, firstName: String, nickName: String, email: String, password: String)
case class User(id: Option[String], name: String, firstName: String, nickName: String, email: String, password: String)

object User {
  implicit val userFormat = Json.format[User]

}

object SignUp {
  implicit val signUpFormat = Json.format[SignUp]
}

object SignIn {
  implicit val signInFormat = Json.format[SignIn]
}