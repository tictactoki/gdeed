package models

import java.util.Date

import models.commons.CollectionFields
import play.api.data.Forms._
import play.api.data.{Mapping, Form}
import play.api.libs.json._
import commons.CollectionFields._
import commons.{CollectionFields => CF}
import User._
import commons.CollectionEnumFieldValues.EventTypeValue._

import scala.util.Try
import scala.util.parsing.json.JSONObject

/**
  * Created by stephane on 18/07/2016.
  */
sealed trait EventType

object EventType {
  def apply(eventType: EventType) = eventType match {
    case LostFound => LostFoundEventValue
    case Help => HelpEventValue
    case _ => throw new Exception("event doesn't exist")
  }

  def unapply(eventType: String) = eventType match {
    case "LostFound" => Some(LostFound)
    case "Help" => Some(Help)
    case _ => None
  }
}

case object LostFound extends EventType

case object Help extends EventType

object Event {

  import Question._

  implicit val eventReads: Reads[Event] = new Reads[Event]{
    override def reads(json: JsValue): JsResult[Event] = json match {
      case obj: JSONObject =>
        (obj \ CF.EventType).as[String] match {
          case "LostFound" => LostFoundEvent.lostEventFormat.reads(json)
          case "Help" => HelpEvent.helpEventFormat.reads(json)
        }
      case _ => JsError()
    }
  }

  implicit val eventWrites: OWrites[Event] = new OWrites[Event] {
    override def writes(o: Event): JsObject = o match {
      case loe:LostFoundEvent => LostFoundEvent.lostEventFormat.writes(loe)
      case he:HelpEvent => HelpEvent.helpEventFormat.writes(he)
    }
  }

  def apply(id: Option[String], owner: Option[User], eventType: String,
            title: String, description: String,
            participants: Option[Set[String]], isOpen: Boolean,
            creationDate: Date, endDate: Date,
            questions: Option[Set[Question]]): Event = {
    println(eventType)
    eventType match {
      case "LostFound" => LostFoundEvent(id,owner,title,description, isOpen,creationDate,endDate,questions.getOrElse(Set.empty))
      case "Help" => HelpEvent(id,owner,title,description,participants.getOrElse(Set.empty),isOpen,creationDate,endDate)
    }
  }

  val eventForm = Form(
    tuple(
      Id -> optional(nonEmptyText),
      Owner -> optional(userMapping),
      CF.EventType -> text,
      Title -> text(2),
      Description -> text,
      Participants -> optional(set(text)),
      IsOpen -> boolean,
      StartDate -> date,
      EndDate -> date,
      Questions -> optional(set(questionMapping))
    )
    //eventMapping
  )

  val eventMapping = mapping(
    Id -> optional(nonEmptyText),
    Owner -> optional(userMapping),
    CF.EventType -> text,
    Title -> text(2),
    Description -> text,
    Participants -> optional(set(text)),
    IsOpen -> checked(IsOpen),
    StartDate -> date,
    EndDate -> date,
    Questions -> optional(set(questionMapping))
  )(Event.apply){ event =>
    event match {
      case l: LostFoundEvent =>
        Some(l._id, l.owner, l.eventType, l.title, l.description, Option.empty[Set[String]], l.isOpen, l.creationDate,
          l.endDate, Option(l.questions))
      case h: HelpEvent =>
        Some(h._id, h.owner, h.eventType, h.title, h.description, Option(h.participants), h.isOpen, h.creationDate,
          h.endDate, Option.empty[Set[Question]])
    }
  }
}


sealed abstract class Event(val _id: Option[String], val owner: Option[User], val eventType: String, val title: String, val description: String, val isOpen: Boolean = true, val creationDate: Date = new Date, val endDate: Date)

case class Choice(_id: Option[String], name: String, value: Boolean)

object Choice {

  implicit val choiceFormat = Json.format[Choice]

  val choiceMapping = mapping(
    Id -> optional(nonEmptyText),
    Name -> text,
    Value -> boolean
  )(Choice.apply)(Choice.unapply)

}

case class Question(_id: Option[String], question: String, choices: Set[Choice] = Set.empty)

object Question {

  import Choice._

  implicit val questionFormat = Json.format[Question]

  val questionMapping = mapping(
    Id -> optional(nonEmptyText),
    CF.Question -> text,
    Choices -> set(choiceMapping)
  )(Question.apply)(Question.unapply)


}

case class LostFoundEvent(override val _id: Option[String],
                          override val owner: Option[User],
                          override val title: String,
                          override val description: String,
                          override val isOpen: Boolean,
                          override val creationDate: Date,
                          override val endDate: Date,
                          questions: Set[Question] = Set.empty)
  extends Event(_id, owner, EventType(LostFound), title, description, isOpen, creationDate, endDate)

object LostFoundEvent {

  import Question._

  implicit val lostEventFormat = Json.format[LostFoundEvent]

  val lostForm = mapping(
    Id -> optional(nonEmptyText),
    Owner -> optional(userMapping),
    Title -> text(2),
    Description -> text,
    IsOpen -> checked(IsOpen),
    StartDate -> date,
    EndDate -> date,
    Questions -> set(questionMapping)
  )(LostFoundEvent.apply)(LostFoundEvent.unapply)


  /*def unapply(_id: Option[String], owner: User, title: String, description: String, isOpen: Boolean, creationDate: Date, endDate: Date, questions: Set[Question]) = {
    Some(LostFoundEvent(_id,owner,title,description,isOpen,creationDate,endDate,questions))
  }*/


}

object HelpEvent {
  implicit val helpEventFormat = Json.format[HelpEvent]

  val helpMapping = mapping(
    Id -> optional(nonEmptyText),
    Owner -> optional(userMapping),
    Title -> text(2),
    Description -> text,
    Participants -> set(text),
    IsOpen -> checked(IsOpen),
    StartDate -> date,
    EndDate -> date
  )(HelpEvent.apply)(HelpEvent.unapply)

}

case class HelpEvent(override val _id: Option[String],
                     override val owner: Option[User],
                     override val title: String,
                     override val description: String,
                     participants: Set[String] = Set.empty,
                     override val isOpen: Boolean,
                     override val creationDate: Date,
                     override val endDate: Date
                    )
  extends Event(_id, owner, EventType(Help), title, description, isOpen, creationDate, endDate)