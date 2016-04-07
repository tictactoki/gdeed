package models

import play.api.libs.json.Json

/**
  * Created by stephane on 04/04/2016.
  */
case class SignIn(email: String, password: String)
case class SignUp(name: String, firstName: String, nickName: String, password: String, email: String)
case class User(_id: Option[String], name: String, firstName: String, password: String, email: String)

object User {
  implicit val userFormat = Json.format[User]
}

object SignUp {
  implicit val signUpFormat = Json.format[SignUp]
}

object SignIn {
  implicit val signInFormat = Json.format[SignIn]
}