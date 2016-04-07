package models.commons

import java.util.UUID

/**
  * Created by stephane on 07/04/2016.
  */
object Helpers {

  def generateId = UUID.randomUUID().toString

}
