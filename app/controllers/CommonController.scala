package controllers

import com.google.inject.{Inject, Singleton}
import models.MongoCrud
import play.api.mvc.Controller
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}

import scala.concurrent.ExecutionContext

/**
  * Created by stephane on 07/04/2016.
  */
@Singleton
abstract class CommonController @Inject ()(val reactiveMongoApi: ReactiveMongoApi)(implicit exec: ExecutionContext)
  extends Controller with MongoController with ReactiveMongoComponents with MongoCrud
