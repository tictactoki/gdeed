package controllers.interfaces

import controllers.GroupController
import models.{User, Group}
import models.commons.CollectionFields._
import play.api.libs.json.Json

/**
  * Created by stephane on 13/07/2016.
  */
trait GroupControllerInterface { self: GroupController =>

  protected val getUserParticipants =  (set: Set[String]) => users.flatMap { collection =>
    val query = Json.obj(Id -> Json.obj("$in" -> Json.toJson(set)))
    collection.find(query).cursor[User]().collect[List]()
  }

  protected def getGroupUserParticiple(id: String) = {
    val query = Json.obj("$in" -> Json.arr(Json.obj(Participants -> id)))
    mainCollection.flatMap { _.find(query).cursor[Group]().collect[List]() }
  }

  protected def getGroupFromId(groupId: String) = {
    mainCollection.flatMap { collection =>
      collection.find(Json.obj(Id -> groupId)).cursor[Group]().collect[List]().map { list =>
        list.headOption
      }
    }
  }

  protected def addParticipant(group: Group, uid: String) = {
    val modifier = Json.obj("$set" -> Json.obj(Participants -> (group.participants + uid)))
    mainCollection.flatMap { collection =>
      collection.update(Json.obj(Id -> group._id.get),modifier)
    }
  }

}
