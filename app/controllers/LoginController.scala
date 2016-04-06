package controllers

import com.google.inject.{Inject, Singleton}
import play.api.mvc.Controller
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}

import scala.concurrent.ExecutionContext

/**
  * Created by stephane on 04/04/2016.
  */
@Singleton
class LoginController @Inject ()(val reactiveMongoApi: ReactiveMongoApi)(implicit exec: ExecutionContext)
  extends Controller with MongoController with ReactiveMongoComponents {





}
