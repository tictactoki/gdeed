package models

import play.api.libs.json.Json
import commons.CollectionFields._
import play.api.data.Form
import play.api.data.Forms._
/**
  * Created by stephane on 04/04/2016.
  */
case class SignIn(email: String, password: String)
case class SignUp(name: String, firstName: String, nickName: String, email: String, password: String)
case class User(_id: Option[String], name: String, firstName: String, nickName: String, email: String, password: String)

object User {
  implicit val userFormat = Json.format[User]
}

object SignUp {
  implicit val signUpFormat = Json.format[SignUp]

  lazy val signUpForm = Form (
    mapping (
      Name -> nonEmptyText(2),
      FirstName -> nonEmptyText(2),
      NickName -> nonEmptyText(3),
      Email -> email,
      Password -> nonEmptyText(6)
    )(SignUp.apply)(SignUp.unapply)
  )

}

object SignIn {
  implicit val signInFormat = Json.format[SignIn]

  lazy val signInForm = Form (
    mapping(
      Email -> email,
      Password -> nonEmptyText(6)
    )(SignIn.apply)(SignIn.unapply)
  )
}