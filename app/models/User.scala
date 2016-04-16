package models

import play.api.libs.json.Json
import commons.CollectionFields._
import play.api.data.Form
import play.api.data.Forms._
/**
  * Created by stephane on 04/04/2016.
  */
case class SignIn(email: String, password: String)
case class User(_id: Option[String], name: String, firstName: String, nickName: String, email: String, password: String)

object User {
  implicit val userFormat = Json.format[User]

  val userMapping = mapping(
    Id -> optional(nonEmptyText),
    Name -> nonEmptyText(2),
    FirstName -> nonEmptyText(2),
    NickName -> nonEmptyText(3),
    Email -> email,
    Password -> nonEmptyText(6)
  ) (User.apply)(User.unapply)

  val userForm = Form(userMapping)

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