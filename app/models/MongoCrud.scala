package models

import controllers.CommonController
import models.commons.CollectionFields._
import play.api.libs.json.{JsObject, Json}
import reactivemongo.api.ReadPreference
import reactivemongo.play.json.collection.JSONCollection
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by stephane on 07/04/2016.
  */
trait MongoCrud { self: CommonController =>

  /**
    *
    * @param name
    * @return
    */
  protected def getJSONCollection(name: String): Future[JSONCollection] = self.database.map(_.collection[JSONCollection](name))

  protected def simplePredicate[T]() = ???

  protected def checkIdExist() = ???

  protected def update = ???

  protected def create = ???

  protected def delete = ???


  /**
    *
    * @param newId
    * @param collectionName
    * @return
    */
  /*protected def checkIdExist(newId: String, collectionName: String): Future[Boolean] = {
    for {
      collection <- getJSONCollection(collectionName)
      id <- collection.find(Json.obj(Id -> newId)).cursor[JsObject](ReadPreference.primary).collect[List]()
    } yield (id != null || id != Nil)
  }*/

}
