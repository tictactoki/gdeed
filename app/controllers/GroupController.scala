package controllers

import com.google.inject.{Inject, Singleton}
import controllers.actions.MongoCrud
import models.Group
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.commands.WriteResult
import models.commons.MongoCollectionNames._
import models.commons.CollectionFields._
import play.api.mvc.Action
import models.Group._
import models.commons.Helpers
import play.api.libs.json.Json
import reactivemongo.api.ReadPreference
import play.modules.reactivemongo.json._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

/**
  * Created by stephane on 14/04/2016.
  */
@Singleton
class GroupController @Inject()(override val reactiveMongoApi: ReactiveMongoApi)(implicit context: ExecutionContext)
  extends CommonController(reactiveMongoApi) with MongoCrud[Group] {

  override implicit lazy val mainCollection = getJSONCollection(Groups)

  override protected def insert(elt: Group): Future[WriteResult] = mainCollection.flatMap(_.insert(elt))

  def createGroup = Action.async(parse.json) { request =>
    request.session.get(Id).map { id =>
      Group.groupForm.bind(request.body).fold(
        hasErrors => getJsonFormErrorResult[Group](hasErrors),
        g => {
          val groupId = Helpers.generateBsonId
          val wr = getUserFromId(id).map { owner =>
            // Normally user always exist here because we want to search the own user data
            Try {
              val group = Group(Some(groupId), owner, g.title, g.description, g.participants, g.messages)
              insert(group)
              group
            }.toOption
          }
          wr.map {
            OkOrNot[Group](_)(getJsonResult(_), BadRequest(Json.toJson(g)))
          }
        }
      )
    }.getOrElse(Future.successful(Unauthorized("You are not connected")))
  }

  def getOwnGroups = Action.async { request =>
    request.session.get(Id).map { id =>
      mainCollection.flatMap { collection =>
        collection.find(Json.obj(Id -> id)).cursor[Group]().collect[List]().map { list =>
          Ok(Json.toJson(list))
        }
      }
    }.getOrElse(Future.successful(Unauthorized("You are not connected")))
  }

  protected def getGroupFromId(groupId: String) = {
    mainCollection.flatMap { collection =>
      collection.find(Json.obj(Id -> groupId)).cursor[Group]().collect[List]().map { list =>
        list.headOption
      }
    }
  }

  /*def addUserOnGroup(groupId: String, userId: String) = {
    val jsObj = Json.obj(Id -> groupId, Owner -> userId)
    for {
      exist <- checkFieldExist(jsObj)
      u <- getUserFromId(userId)
      g <- getGroupFromId(groupId)
    } yield {
      if (!exist && g.isDefined) {
        val set = g.get.participants
        val newSet = set ++ u
        val res = groups.flatMap { collection =>
          collection.findAndUpdate(Json.obj(Id -> groupId),Json.obj("$set" -> Json.obj(Participants -> newSet)),true).map(_.result[Group])
        }
        res
      }
      else Future.successful(g)
    }.flatMap{ o =>
      o.get.
    }
  }*/

}
