//> using scala "3.5.0"
//> using jsVersion "1.20.2"
//> using platform scala-js
//> using dep "org.virtuslab::k6-scala::dev"
package example

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import org.virtuslab.scalajs.k6.http.*
import org.virtuslab.scalajs.k6.*
import org.virtuslab.scalajs.k6.options.*
import org.virtuslab.scalajs.k6.html.*
import scala.concurrent.duration.*

object HtmlFormsExample {
  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    // --- Response.html() — parse HTML and traverse DOM ---
    val loginPage = http.get("https://test.k6.io/my_messages.php")
    check(
      loginPage,
      Checkers("login page status is 200" -> Check[Response](_.status == 200))
    )

    val doc = loginPage.html()
    val inputs = doc.find("input")
    println(s"Form inputs found: ${inputs.size()}")

    if (inputs.size() > 0) {
      val firstInput = inputs.first()
      println(s"First input type: ${firstInput.attr("type").getOrElse("unknown")}")
      println(s"First input name: ${firstInput.attr("name").getOrElse("unknown")}")
    }

    // html(selector) scopes the Selection to matching elements
    val forms = loginPage.html("form")
    println(s"Number of forms: ${forms.size()}")

    sleep(100.millis)

    // --- Response.submitForm() — submit a form with field overrides ---
    val formResponse = loginPage.submitForm(
      SubmitFormParams(
        formSelector = Some("form"),
        fields = Some(Map("login" -> "admin", "password" -> "123"))
      )
    )
    check(
      formResponse,
      Checkers(
        "form submit status ok" -> Check[Response](r => r.status >= 200 && r.status < 400)
      )
    )
    println(s"Form submitted, response status: ${formResponse.status}")

    sleep(100.millis)

    // --- Response.clickLink() — follow a link ---
    val pageWithLinks = formResponse
    val links = pageWithLinks.html().find("a")
    println(s"Links on post-login page: ${links.size()}")

    if (links.size() > 0) {
      val clickResponse = pageWithLinks.clickLink()
      check(
        clickResponse,
        Checkers(
          "click link status ok" -> Check[Response](r => r.status >= 200 && r.status < 400)
        )
      )
      println(s"Clicked link, navigated to: ${clickResponse.url}")
    }

    // Edge cases (documented, not executed to avoid runtime errors):
    // - submitForm() on a response with no <form> element throws a k6 runtime error
    // - clickLink() with no matching link throws a k6 runtime error
    // - clickLink() with no args uses the default selector "a[href]"
    // - submitForm() with no args finds the first <form> in the response body

    sleep(100.millis)
  }

  @JSExportTopLevel("options")
  val options = Options(vus = Some(1), iterations = Some(1))
}
