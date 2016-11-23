package controllers

import com.google.inject.{Inject, Singleton}
import controllers.actions.MongoCrud
import models.Event
import play.api.mvc.Action
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.commands.WriteResult
import reactivemongo.play.json.collection.JSONCollection
import models.commons.MongoCollectionNames._
import scala.concurrent.{Future, ExecutionContext}
import models.commons.CollectionFields._

/**
  * Created by stephane on 18/07/2016.
  */
@Singleton
class EventController @Inject()(override val reactiveMongoApi: ReactiveMongoApi)(implicit context: ExecutionContext)
  extends CommonController(reactiveMongoApi) with MongoCrud[Event] {

  override implicit val mainCollection: Future[JSONCollection] = getJSONCollection(Events)

  override protected def insert(elt: Event): Future[WriteResult] = mainCollection.flatMap(_.insert(elt))

  def createEvent = Action.async { implicit request =>
    request.session.get(Id).map { id =>

    }
    ???
  }

}