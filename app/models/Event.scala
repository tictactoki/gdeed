package models

import java.util.Date

import play.api.data.Forms._
import play.api.data.Form
import play.api.libs.json.Json
import commons.CollectionFields._
import User._

/**
  * Created by stephane on 18/07/2016.
  */
case class Event(_id: Option[String], owner: User, participants: Set[String] = Set(), title: String,  description: String, startDate: Date, endDate: Date)

object Event {

  implicit val eventFormat = Json.format[Event]

  val eventForm = Form(
    mapping(
      Id -> optional(nonEmptyText),
      Owner -> userMapping,
      Participants -> set(text),
      Title -> text(2),
      Description -> text,
      StartDate -> date,
      EndDate -> date
    ) (Event.apply)(Event.unapply)
  )


}