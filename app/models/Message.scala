package models

import play.api.libs.json.Json
import commons.CollectionFields._
import play.api.data.{Form, Forms, Mapping}
import play.api.data.Forms._
/**
  * Created by stephane on 14/04/2016.
  */
case class Message(_id: Option[String], owner: User, content: String, answers: List[Message] = Nil)

object Message {
  implicit val messageFormat = Json.format[Message]

  /**
    * declare the mapping type for recursive mapping
    */
  val messageMapping: Mapping[Message] = mapping(
    Id -> optional(nonEmptyText),
    Owner -> User.userMapping,
    Content -> nonEmptyText,
    Answers -> list(messageMapping)
  )(Message.apply)(Message.unapply)

}