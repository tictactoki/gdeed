package models.commons

/**
  * Created by stephane on 05/04/2016.
  */
object CollectionFields {

  final val Id = "_id"
  final val Name = "name"
  final val FirstName = "firstName"
  final val Password = "password"
  final val NickName = "nickName"
  final val Email = "email"
  final val Title = "title"
  final val Owner = "owner"
  final val Content = "content"
  final val Description = "description"
  final val IsOpen = "isOpen"
  final val EventType = "eventType"

  final val Participants = "participants"
  final val Messages = "messages"
  final val Answers = "answers"

  final val StartDate = "startDate"
  final val EndDate = "endDate"


  final val Question = "question"
  final val Value = "value"
  final val Choices = "choices"
  final val Questions = "questions"
}


object CollectionEnumFieldValues {

  object EventTypeValue {
    final val LostFoundEventValue = "lostFoundEvent"
    final val HelpEventValue = "helpEvent"
  }

}