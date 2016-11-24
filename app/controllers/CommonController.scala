package controllers

import com.google.inject.{Inject, Singleton}
import models.{Part, User}
import models.commons.CollectionFields._
import play.api.Configuration
import play.api.data.Form
import play.api.libs.json.{JsObject, JsValue, Json, Writes}
import play.api.mvc.{Controller, Result}

import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.play.json.collection.JSONCollection
import play.modules.reactivemongo.json._

import scala.concurrent.{ExecutionContext, Future}
import models.commons.MongoCollectionNames._
import reactivemongo.api.ReadPreference
import reactivemongo.api.commands.WriteResult

/**
  * Created by stephane on 07/04/2016.
  */
abstract class CommonController @Inject ()(val reactiveMongoApi: ReactiveMongoApi, implicit val config: Configuration, implicit val webJarAssets: WebJarAssets)(implicit exec: ExecutionContext)
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
 *
    * @param form
    * @tparam T
    * @return
    */
  protected def getJsonFormErrorResult[T](form: Form[T]): Future[Result] = {
    val jsError = form.errors.foldLeft(Json.obj())( (acc,js) => acc ++ Json.obj(js.key -> js.message))
    Future.successful(BadRequest(jsError))
  }

  protected def getSeqFromError[T](form: Form[T]) = {
   form.errors.map { fe =>
     (fe.key -> fe.message)
   }
  }

  protected def getUserFromId(id: String): Future[Option[User]] = {
    users.flatMap(_.find(Json.obj(Id -> id)).cursor[User]().collect[List]().map(l => l.headOption))
  }


  /**
    *
    * @param id
    * @param collection
    * @return delete the data with this @id on this @collection
    */
  protected def delete(id: String)(implicit collection: Future[JSONCollection]): Future[WriteResult] = {
    collection.flatMap(_.remove(Json.obj(Id -> id)))
  }

  /**
    *
    * @param fieldName
    * @param fieldInput
    * @param collection
    * @return true if @fieldInput with the @fieldName exist on this collection
    */
  protected def checkFieldExist(fieldName: String, fieldInput: String)(implicit collection: Future[JSONCollection]): Future[Boolean] = {
    checkFieldExist(Json.obj(fieldName -> fieldInput))
  }

  protected def checkFieldExist(obj: JsObject)(implicit collection: Future[JSONCollection]): Future[Boolean] = {
    for {
      collection <- collection
      exist <- collection.find(obj).cursor[JsObject](ReadPreference.primary).collect[List]()
    } yield exist != null && exist != Nil
  }

}