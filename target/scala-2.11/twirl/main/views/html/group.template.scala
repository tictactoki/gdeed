
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object group_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class group extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Option[Seq[scala.Tuple2[String, String]]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(form: Option[Seq[(String,String)]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.38*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html>
    <head>
        <title>Sign In</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(/*7.54*/routes/*7.60*/.Assets.versioned("stylesheets/main.css")),format.raw/*7.101*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(/*8.59*/routes/*8.65*/.Assets.versioned("images/favicon.png")),format.raw/*8.104*/("""">

    </head>
    <body>

        <div id="content"></div>
        <form action='"""),_display_(/*14.24*/routes/*14.30*/.GroupController.createGroup()),format.raw/*14.60*/("""' method="POST">
            <input id="email" refs="email" name="email" type="email" placeholder="Email"required/>
            <input id="password" refs="password" name="password" type="password" placeholder="Password"/>
            <input id="email" refs="email" name="email" type="email" placeholder="Email"required/>
            <input id="password" refs="password" name="password" type="password" placeholder="Password"/>
            <input type="submit" value="OK"/>
        </form>


        <script type='text/javascript' src='"""),_display_(/*23.46*/routes/*23.52*/.Assets.versioned("bower_components/react/react.js")),format.raw/*23.104*/("""'></script>
        <script type='text/javascript' src='"""),_display_(/*24.46*/routes/*24.52*/.Assets.versioned("bower_components/react/react-dom.js")),format.raw/*24.108*/("""'></script>
        <script type='text/javascript' src='"""),_display_(/*25.46*/routes/*25.52*/.Assets.versioned("bower_components/jquery/dist/jquery.js")),format.raw/*25.111*/("""'></script>
        <script type='text/javascript' src='"""),_display_(/*26.46*/routes/*26.52*/.Assets.versioned("javascripts/login.jsx")),format.raw/*26.94*/("""'></script>

    </body>
</html>

"""))
      }
    }
  }

  def render(form:Option[Seq[scala.Tuple2[String, String]]]): play.twirl.api.HtmlFormat.Appendable = apply(form)

  def f:((Option[Seq[scala.Tuple2[String, String]]]) => play.twirl.api.HtmlFormat.Appendable) = (form) => apply(form)

  def ref: this.type = this

}


}

/**/
object group extends group_Scope0.group
              /*
                  -- GENERATED --
                  DATE: Tue Oct 04 15:12:41 CEST 2016
                  SOURCE: /Users/stephane/workspace/gdeed/app/views/group.scala.html
                  HASH: 57edbf846f60f4318006561f966556d0779961d3
                  MATRIX: 562->1|693->37|721->39|865->157|879->163|941->204|1028->265|1042->271|1102->310|1213->394|1228->400|1279->430|1842->966|1857->972|1931->1024|2015->1081|2030->1087|2108->1143|2192->1200|2207->1206|2288->1265|2372->1322|2387->1328|2450->1370
                  LINES: 20->1|25->1|27->3|31->7|31->7|31->7|32->8|32->8|32->8|38->14|38->14|38->14|47->23|47->23|47->23|48->24|48->24|48->24|49->25|49->25|49->25|50->26|50->26|50->26
                  -- GENERATED --
              */
          