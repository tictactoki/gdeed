package controllers

import com.google.inject.{Inject, Singleton}
import models.{MongoCrud, Part}
import play.api.mvc.Controller
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by stephane on 07/04/2016.
  */
abstract class CommonController @Inject ()(val reactiveMongoApi: ReactiveMongoApi)(implicit exec: ExecutionContext)
  extends Controller with MongoController with ReactiveMongoComponents {

  /**
    *
    * @param name
    * @return Future[JSONCollection]
    */
  protected def getJSONCollection(name: String): Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection](name))

}
