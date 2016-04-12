package controllers.actions

import models.commons.CollectionFields._
import play.api.libs.json.{JsObject, Json}
import play.api.mvc.{Result, Results}
import play.modules.reactivemongo.json._
import reactivemongo.api.ReadPreference
import reactivemongo.api.commands.WriteResult
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by stephane on 07/04/2016.
  * Interface for mongo controller
  * Need to declare implicit the main collection controller in class Controller
  */

trait MongoCrud[T] {

  protected def simplePredicate() = ???

  protected def update(id: String, field: String)(constraint: T => Boolean)(implicit collection: JSONCollection) = ???

  protected def create(elt: T): Future[WriteResult]

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
    for {
      collection <- collection
      id <- collection.find(Json.obj(fieldName -> fieldInput)).cursor[JsObject](ReadPreference.primary).collect[List]()
    } yield id != null && id != Nil
  }

  protected def checkFieldExist(obj: JsObject)(implicit collection: Future[JSONCollection]): Future[Boolean] = {
    for {
      collection <- collection
      exist <- collection.find(obj).cursor[JsObject](ReadPreference.primary).collect[List]()
    } yield exist != null && exist != Nil
  }

  /**
    *
    * @param option
    * @param exist
    * @param errors
    * @tparam T
    * @return Ok result on json format if the result exist or appropriate result "errors"
    */
  protected def OkOrNot[T](option: Option[T])(exist: T => Result, errors: Result) = option match {
    case Some(something) => exist(something)
    case None => errors
  }

}
