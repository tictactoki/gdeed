
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object signUp_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

class signUp extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[User],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(form: Form[User]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.20*/("""

"""))
      }
    }
  }

  def render(form:Form[User]): play.twirl.api.HtmlFormat.Appendable = apply(form)

  def f:((Form[User]) => play.twirl.api.HtmlFormat.Appendable) = (form) => apply(form)

  def ref: this.type = this

}


}

/**/
object signUp extends signUp_Scope0.signUp
              /*
                  -- GENERATED --
                  DATE: Tue Oct 04 15:12:41 CEST 2016
                  SOURCE: /Users/stephane/workspace/gdeed/app/views/signUp.scala.html
                  HASH: 20fa3e30ca95272ac9c74353313aaf19a856ced9
                  MATRIX: 533->1|646->19
                  LINES: 20->1|25->1
                  -- GENERATED --
              */
          