
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object login_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class login extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Option[Seq[scala.Tuple2[String, String]]],play.twirl.api.HtmlFormat.Appendable] {

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
        <form action='"""),_display_(/*14.24*/routes/*14.30*/.UserController.signIn()),format.raw/*14.54*/("""' method="POST">
            <input id="email" refs="email" name="email" type="email" placeholder="Email"required/>
            <input id="password" refs="password" name="password" type="password" placeholder="Password"/>
            <input type="submit" value="OK"/>
        </form>


        <script type='text/javascript' src='"""),_display_(/*21.46*/routes/*21.52*/.Assets.versioned("bower_components/react/react.js")),format.raw/*21.104*/("""'></script>
        <script type='text/javascript' src='"""),_display_(/*22.46*/routes/*22.52*/.Assets.versioned("bower_components/react/react-dom.js")),format.raw/*22.108*/("""'></script>
        <script type='text/javascript' src='"""),_display_(/*23.46*/routes/*23.52*/.Assets.versioned("bower_components/jquery/dist/jquery.js")),format.raw/*23.111*/("""'></script>
        <script type='text/javascript' src='"""),_display_(/*24.46*/routes/*24.52*/.Assets.versioned("javascripts/login.jsx")),format.raw/*24.94*/("""'></script>


        """),_display_(/*27.10*/form/*27.14*/.map/*27.18*/ { seq =>_display_(Seq[Any](format.raw/*27.27*/("""
            """),format.raw/*28.13*/("""<script type="text/javascript">
                    var o = """),format.raw/*29.29*/("""{"""),format.raw/*29.30*/("""}"""),format.raw/*29.31*/(""";
                    var a = '"""),_display_(/*30.31*/seq(0)/*30.37*/._1),format.raw/*30.40*/("""';
                    var v = '"""),_display_(/*31.31*/seq(0)/*31.37*/._2),format.raw/*31.40*/("""';
                    console.log(a,v);
                    """),_display_(/*33.22*/seq/*33.25*/.foreach/*33.33*/ { fe =>_display_(Seq[Any](format.raw/*33.41*/(""" """),format.raw/*33.42*/("""o[""""),_display_(/*33.46*/fe/*33.48*/._1),format.raw/*33.51*/(""""] = """"),_display_(/*33.58*/fe/*33.60*/._2),format.raw/*33.63*/(""""; """)))}),format.raw/*33.67*/("""

                    """),format.raw/*35.21*/("""test(o);
            </script>
        """)))}),format.raw/*37.10*/("""
    """),format.raw/*38.5*/("""</body>
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
object login extends login_Scope0.login
              /*
                  -- GENERATED --
                  DATE: Tue Oct 04 15:12:41 CEST 2016
                  SOURCE: /Users/stephane/workspace/gdeed/app/views/login.scala.html
                  HASH: 8e66bb185cf0befc0ade633a1d24ccff8fb5b7bb
                  MATRIX: 562->1|693->37|721->39|865->157|879->163|941->204|1028->265|1042->271|1102->310|1213->394|1228->400|1273->424|1631->755|1646->761|1720->813|1804->870|1819->876|1897->932|1981->989|1996->995|2077->1054|2161->1111|2176->1117|2239->1159|2289->1182|2302->1186|2315->1190|2362->1199|2403->1212|2491->1272|2520->1273|2549->1274|2608->1306|2623->1312|2647->1315|2707->1348|2722->1354|2746->1357|2835->1419|2847->1422|2864->1430|2910->1438|2939->1439|2970->1443|2981->1445|3005->1448|3039->1455|3050->1457|3074->1460|3109->1464|3159->1486|3230->1526|3262->1531
                  LINES: 20->1|25->1|27->3|31->7|31->7|31->7|32->8|32->8|32->8|38->14|38->14|38->14|45->21|45->21|45->21|46->22|46->22|46->22|47->23|47->23|47->23|48->24|48->24|48->24|51->27|51->27|51->27|51->27|52->28|53->29|53->29|53->29|54->30|54->30|54->30|55->31|55->31|55->31|57->33|57->33|57->33|57->33|57->33|57->33|57->33|57->33|57->33|57->33|57->33|57->33|59->35|61->37|62->38
                  -- GENERATED --
              */
          