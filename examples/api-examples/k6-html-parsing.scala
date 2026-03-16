//> using scala "3.5.0"
//> using jsVersion "1.20.2"
//> using platform scala-js
//> using dep "org.virtuslab::k6-scala::dev"

package example

import scala.scalajs.js.annotation.*
import scala.concurrent.duration.*

import org.virtuslab.scalajs.k6.*
import org.virtuslab.scalajs.k6.http.*
import org.virtuslab.scalajs.k6.html.*
import org.virtuslab.scalajs.k6.options.*

object HtmlParsingExample {

  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    val res = http.get("https://test.k6.io")
    check(
      res,
      Checkers("status is 200" -> Check[Response](_.status == 200))
    )

    val doc      = parseHTML(res.body)
    val headings = doc.find("h1, h2, h3")

    println(s"Total headings: ${headings.size()}")

    if (headings.size() > 0) {
      val first = headings.first()
      println(s"First heading text: ${first.text()}")
    }

    val links     = doc.find("a")
    val linkArray = links.toArray()
    println(s"Total links: ${links.size()}, first href: ${linkArray.headOption.map(_.getAttribute("href")).getOrElse("")}")

    val forms = doc.find("form")
    if (forms.size() > 0) {
      val firstForm = forms.first()
      println(s"Form serialized: ${firstForm.serialize()}")
      val serializedObj = firstForm.serializeObject()
      println(s"Form fields: ${serializedObj.keys.mkString(",")}")
    }

    sleep(100.millis)
  }

  @JSExportTopLevel("options")
  val options: Options = Options(vus = Some(1), iterations = Some(1))
}

