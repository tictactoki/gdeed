package models

import controllers.CommonController
import models.commons.CollectionFields._
import models.commons.Helpers
import play.api.libs.json.{JsObject, Json}
import reactivemongo.api.ReadPreference
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import play.modules.reactivemongo.json._
import reactivemongo.api.commands.WriteResult

/**
  * Created by stephane on 07/04/2016.
  */

trait MongoCrud[T] {

  protected def simplePredicate() = ???

  protected def update(id: String, field: String)(constraint: T => Boolean)(implicit collection: JSONCollection) = ???

  protected def create(elt: T): Future[WriteResult]

  protected def delete(id: String)(implicit collection: Future[JSONCollection]): Future[WriteResult] = {
    collection.flatMap(_.remove(Json.obj(Id -> id)))
  }

  protected def checkFieldExist(fieldName: String, fieldInput: String)(implicit collection: Future[JSONCollection]): Future[Boolean] = {
    collection.map { collection =>
      val id = collection.find(Json.obj(fieldName -> fieldInput)).cursor[JsObject](ReadPreference.primary).collect[List]()
      id != null && id != Nil
    }
  }

  protected def createId(implicit collection: Future[JSONCollection]): Future[String] = {
    val id = Helpers.generateId
    for {
      b <- checkFieldExist(Id, id)
      value <- if (b) createId else Future.successful(id)
    } yield value
  }

}
