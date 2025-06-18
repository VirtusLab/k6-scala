//> using scala "3.5.0"
//> using jsVersion "1.18.1"
//> using platform scala-js
//> using dep "org.scalajs::k6-scala::0.0.1-SNAPSHOT"
package example

import scala.scalajs.js.annotation.*
import com.github.wiacekm.scalajs.k6.http
import com.github.wiacekm.scalajs.k6.http.*
import com.github.wiacekm.scalajs.k6.*
import scala.concurrent.duration.*

object K6ScalaExample {
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
}
