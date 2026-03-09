//> using scala "3.5.0"
//> using jsVersion "1.20.2"
//> using platform scala-js
//> using dep "org.virtuslab::k6-scala::dev"
package example

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import org.virtuslab.scalajs.k6.http.*
import org.virtuslab.scalajs.k6.http.HttpURL.*
import org.virtuslab.scalajs.k6.*
import org.virtuslab.scalajs.k6.options.*
import org.virtuslab.scalajs.k6.options.Scenario.*
import scala.concurrent.duration.*

object SingleRequest {
  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    val url: URL = "https://test.k6.io"
    val response = http.get(url)
    sleep(1.second)
    check(
      response,
      Checkers(
        "status is 200" -> Check[Response](r => r.status == 200)
      )
    )
  }
  @JSExportTopLevel("options")
  val options = Options(
    vus = Some(1),
    iterations = Some(1)
  )
}
