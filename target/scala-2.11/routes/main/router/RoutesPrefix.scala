
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/stephane/workspace/gdeed/conf/routes
// @DATE:Tue Oct 04 15:12:40 CEST 2016


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
