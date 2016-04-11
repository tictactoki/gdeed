package models.commons

import reactivemongo.bson.BSONObjectID

/**
  * Created by stephane on 07/04/2016.
  */
object Helpers {

  def generateBsonId = BSONObjectID.generate().stringify

}
