package controllers

import com.google.inject.{Inject, Singleton}
import models.{Part, User}
import models.commons.CollectionFields._
import play.api.data.Form
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.{Controller, Result}
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.play.json.collection.JSONCollection
import play.modules.reactivemongo.json._

import scala.concurrent.{ExecutionContext, Future}
import models.commons.MongoCollectionNames._

/**
  * Created by stephane on 07/04/2016.
  */
abstract class CommonController @Inject ()(val reactiveMongoApi: ReactiveMongoApi)(implicit exec: ExecutionContext)
  extends Controller with MongoController with ReactiveMongoComponents {

  lazy val users = getJSONCollection(Users)

  /**
    *
    * @param name
    * @return Future[JSONCollection]
    */
  protected def getJSONCollection(name: String): Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection](name))

  /**
    *
    * @param elt
    * @param status
    * @param tjs
    * @tparam T
    * @return Result as a json format
    */
  protected def getJsonResult[T](elt: T, status: Status = Ok)(implicit tjs: Writes[T]): Result = status(Json.toJson(elt))

  /**
    * Send errors as a json result
    * @param form
    * @tparam T
    * @return
    */
  protected def getJsonFormErrorResult[T](form: Form[T]): Future[Result] = {
    val jsError = form.errors.foldLeft(Json.obj())( (acc,js) => acc ++ Json.obj(js.key -> js.message))
    Future.successful(BadRequest(jsError))
  }

  protected def getUserFromId(id: String): Future[Option[User]] = {
    users.flatMap(_.find(Json.obj(Id -> id)).cursor[User]().collect[List]().map(l => l.headOption))
  }

}