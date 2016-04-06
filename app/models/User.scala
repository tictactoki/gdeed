package models

import play.api.libs.json.Json

/**
  * Created by stephane on 04/04/2016.
  */
case class User(_id: Option[String], name: String, firstName: String, password: String)

object User {

  implicit val userFormat = Json.format[User]

}
