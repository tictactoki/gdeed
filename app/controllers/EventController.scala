package controllers

import com.google.inject.{Inject, Singleton}
import controllers.actions.MongoCrud
import models.Event
import models.commons.Helpers
import play.api.Configuration
import play.api.libs.json.Json
import play.api.mvc.Action
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.commands.WriteResult
import reactivemongo.play.json.collection.JSONCollection
import models.commons.MongoCollectionNames._
import scala.concurrent.{Future, ExecutionContext}
import models.commons.CollectionFields._
import play.api.i18n.Messages.Implicits._
import models.Event._
import play.modules.reactivemongo.json._

/**
  * Created by stephane on 18/07/2016.
  */
@Singleton
class EventController @Inject()(override val reactiveMongoApi: ReactiveMongoApi, implicit val config: Configuration)(implicit context: ExecutionContext)
  extends CommonController(reactiveMongoApi) with MongoCrud[Event] {

  override implicit val mainCollection: Future[JSONCollection] = getJSONCollection(Events)

  override protected def insert(elt: Event): Future[WriteResult] = mainCollection.flatMap(_.insert(elt))

  def createEvent = Action.async { implicit request =>
      Event.eventForm.bindFromRequest().fold(
        hasError => {
          println(hasError)
          getJsonFormErrorResult(hasError)
        },
        {
          case (i, owner, eventType, title, desc, participants,
          open, creationData, endDate, questions) =>
            val id = Helpers.generateBsonId
            val event = Event(Some(id), owner, eventType, title, desc, participants,
              open, creationData, endDate, questions)
            insert(event)
            Future.successful(Ok(Json.toJson(event)))
        }
      )
  }

  def getEvent = Action.async { implicit request =>
    mainCollection.flatMap { collection =>
      collection.find(Json.obj()).cursor[Event]().collect[List]().map { list =>
        Ok(Json.toJson(list))
      }
    }
  }

  def index = Action { Ok(views.html.event(Event.eventForm)) }

}