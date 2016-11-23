
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/stephane/workspace/gdeed/conf/routes
// @DATE:Tue Oct 04 15:12:40 CEST 2016

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:7
  UserController_2: controllers.UserController,
  // @LINE:9
  CountController_0: controllers.CountController,
  // @LINE:11
  AsyncController_1: controllers.AsyncController,
  // @LINE:21
  GroupController_4: controllers.GroupController,
  // @LINE:29
  Assets_3: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    UserController_2: controllers.UserController,
    // @LINE:9
    CountController_0: controllers.CountController,
    // @LINE:11
    AsyncController_1: controllers.AsyncController,
    // @LINE:21
    GroupController_4: controllers.GroupController,
    // @LINE:29
    Assets_3: controllers.Assets
  ) = this(errorHandler, UserController_2, CountController_0, AsyncController_1, GroupController_4, Assets_3, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, UserController_2, CountController_0, AsyncController_1, GroupController_4, Assets_3, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.UserController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """count""", """controllers.CountController.count"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """message""", """controllers.AsyncController.message"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """signUp/""", """controllers.UserController.signUp"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """signIn/""", """controllers.UserController.signIn"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """user""", """controllers.UserController.getUserFromNickName(nickName:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """users""", """controllers.UserController.getUsers"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """group/create""", """controllers.GroupController.createGroup"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """groups""", """controllers.GroupController.getOwnGroups"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """participants""", """controllers.GroupController.getUserParticipant(id:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:7
  private[this] lazy val controllers_UserController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_UserController_index0_invoker = createInvoker(
    UserController_2.index,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "index",
      Nil,
      "GET",
      """ An example controller showing a sample home page
GET         /                     controllers.HomeController.index""",
      this.prefix + """"""
    )
  )

  // @LINE:9
  private[this] lazy val controllers_CountController_count1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("count")))
  )
  private[this] lazy val controllers_CountController_count1_invoker = createInvoker(
    CountController_0.count,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CountController",
      "count",
      Nil,
      "GET",
      """ An example controller showing how to use dependency injection""",
      this.prefix + """count"""
    )
  )

  // @LINE:11
  private[this] lazy val controllers_AsyncController_message2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("message")))
  )
  private[this] lazy val controllers_AsyncController_message2_invoker = createInvoker(
    AsyncController_1.message,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AsyncController",
      "message",
      Nil,
      "GET",
      """ An example controller showing how to write asynchronous code""",
      this.prefix + """message"""
    )
  )

  // @LINE:14
  private[this] lazy val controllers_UserController_signUp3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("signUp/")))
  )
  private[this] lazy val controllers_UserController_signUp3_invoker = createInvoker(
    UserController_2.signUp,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "signUp",
      Nil,
      "POST",
      """ Users""",
      this.prefix + """signUp/"""
    )
  )

  // @LINE:15
  private[this] lazy val controllers_UserController_signIn4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("signIn/")))
  )
  private[this] lazy val controllers_UserController_signIn4_invoker = createInvoker(
    UserController_2.signIn,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "signIn",
      Nil,
      "POST",
      """""",
      this.prefix + """signIn/"""
    )
  )

  // @LINE:17
  private[this] lazy val controllers_UserController_getUserFromNickName5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("user")))
  )
  private[this] lazy val controllers_UserController_getUserFromNickName5_invoker = createInvoker(
    UserController_2.getUserFromNickName(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "getUserFromNickName",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """user"""
    )
  )

  // @LINE:18
  private[this] lazy val controllers_UserController_getUsers6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("users")))
  )
  private[this] lazy val controllers_UserController_getUsers6_invoker = createInvoker(
    UserController_2.getUsers,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "getUsers",
      Nil,
      "GET",
      """""",
      this.prefix + """users"""
    )
  )

  // @LINE:21
  private[this] lazy val controllers_GroupController_createGroup7_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("group/create")))
  )
  private[this] lazy val controllers_GroupController_createGroup7_invoker = createInvoker(
    GroupController_4.createGroup,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.GroupController",
      "createGroup",
      Nil,
      "POST",
      """Groups""",
      this.prefix + """group/create"""
    )
  )

  // @LINE:23
  private[this] lazy val controllers_GroupController_getOwnGroups8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("groups")))
  )
  private[this] lazy val controllers_GroupController_getOwnGroups8_invoker = createInvoker(
    GroupController_4.getOwnGroups,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.GroupController",
      "getOwnGroups",
      Nil,
      "GET",
      """""",
      this.prefix + """groups"""
    )
  )

  // @LINE:26
  private[this] lazy val controllers_GroupController_getUserParticipant9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("participants")))
  )
  private[this] lazy val controllers_GroupController_getUserParticipant9_invoker = createInvoker(
    GroupController_4.getUserParticipant(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.GroupController",
      "getUserParticipant",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """participants"""
    )
  )

  // @LINE:29
  private[this] lazy val controllers_Assets_versioned10_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned10_invoker = createInvoker(
    Assets_3.versioned(fakeValue[String], fakeValue[Asset]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      """ Map static resources from the /public folder to the /assets URL path""",
      this.prefix + """assets/""" + "$" + """file<.+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:7
    case controllers_UserController_index0_route(params) =>
      call { 
        controllers_UserController_index0_invoker.call(UserController_2.index)
      }
  
    // @LINE:9
    case controllers_CountController_count1_route(params) =>
      call { 
        controllers_CountController_count1_invoker.call(CountController_0.count)
      }
  
    // @LINE:11
    case controllers_AsyncController_message2_route(params) =>
      call { 
        controllers_AsyncController_message2_invoker.call(AsyncController_1.message)
      }
  
    // @LINE:14
    case controllers_UserController_signUp3_route(params) =>
      call { 
        controllers_UserController_signUp3_invoker.call(UserController_2.signUp)
      }
  
    // @LINE:15
    case controllers_UserController_signIn4_route(params) =>
      call { 
        controllers_UserController_signIn4_invoker.call(UserController_2.signIn)
      }
  
    // @LINE:17
    case controllers_UserController_getUserFromNickName5_route(params) =>
      call(params.fromQuery[String]("nickName", None)) { (nickName) =>
        controllers_UserController_getUserFromNickName5_invoker.call(UserController_2.getUserFromNickName(nickName))
      }
  
    // @LINE:18
    case controllers_UserController_getUsers6_route(params) =>
      call { 
        controllers_UserController_getUsers6_invoker.call(UserController_2.getUsers)
      }
  
    // @LINE:21
    case controllers_GroupController_createGroup7_route(params) =>
      call { 
        controllers_GroupController_createGroup7_invoker.call(GroupController_4.createGroup)
      }
  
    // @LINE:23
    case controllers_GroupController_getOwnGroups8_route(params) =>
      call { 
        controllers_GroupController_getOwnGroups8_invoker.call(GroupController_4.getOwnGroups)
      }
  
    // @LINE:26
    case controllers_GroupController_getUserParticipant9_route(params) =>
      call(params.fromQuery[String]("id", None)) { (id) =>
        controllers_GroupController_getUserParticipant9_invoker.call(GroupController_4.getUserParticipant(id))
      }
  
    // @LINE:29
    case controllers_Assets_versioned10_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned10_invoker.call(Assets_3.versioned(path, file))
      }
  }
}
