package models

import play.api.libs.json.Json
import commons.CollectionFields._
import play.api.data.Form
import play.api.data.Forms._
/**
  * Created by stephane on 14/04/2016.
  */
case class Group(_id: Option[String], owner: Option[User], title: String, description: String,
                 participants: Set[String] = Set(), messages: Set[String] = Set()) {

  /**
    *
    * @param user
    * @return true if the user is the owner
    */
  protected def isOwner(user: User): Boolean = user.equals(owner.get)

}

object Group {
  implicit val groupFormat = Json.format[Group]

  val groupForm = Form(
    mapping (
      Id -> optional(nonEmptyText),
      Owner -> optional(User.userMapping),
      Title -> nonEmptyText(2),
      Description -> text,
      Participants -> set(text),
      Messages -> set(text)
    )(Group.apply)(Group.unapply)
  )

}