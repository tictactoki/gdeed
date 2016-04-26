package controllers.actions

import models.commons.CollectionFields._
import play.api.libs.json._
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

  implicit val mainCollection: Future[JSONCollection]

  protected def simplePredicate() = ???

  protected def update(id: String, field: String)(constraint: T => Boolean)(implicit collection: JSONCollection) = ???

  protected def insert(elt: T): Future[WriteResult]


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
