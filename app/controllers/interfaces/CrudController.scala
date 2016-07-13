package controllers.interfaces

/**
  * Created by stephane on 13/07/2016.
  * we don't know on which collection we use crud operation right now
  */
trait CrudController {

  def update[T](id: String, t: T)

}
