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
import scala.concurrent.duration.*

object FormDataExample {
  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    val response = http.post(
      "https://httpbin.test.k6.io/post",
      body = Some(
        formData(
          Map(
            "username" -> "test-user",
            "password" -> "secret"
          )
        )
      ),
      params = Some(
        Params(
          headers = Some(Map("Content-Type" -> "application/x-www-form-urlencoded")),
          responseType = None,
          tags = None,
          timeout = None,
          responseCallback = None
        )
      )
    )

    check(
      response,
      Checkers(
        "status is 2xx" -> Check[Response](r => r.status >= 200 && r.status < 300)
      )
    )

    sleep(100.millis)
  }

  @JSExportTopLevel("options")
  val options = Options(vus = Some(1), iterations = Some(1))
}

