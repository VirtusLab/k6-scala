//> using scala "3.5.0"
//> using jsVersion "1.20.2"
//> using platform scala-js
//> using dep "org.virtuslab::k6-scala::dev"
package example

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import org.virtuslab.scalajs.k6.http.*
import org.virtuslab.scalajs.k6.http.Batch.BatchRequest
import org.virtuslab.scalajs.k6.http.HttpURL.*
import org.virtuslab.scalajs.k6.*
import org.virtuslab.scalajs.k6.options.*
import scala.concurrent.duration.*

object SingleRequest {
  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    val userId = "123"

    val r1 = http.batch(Seq("https://test.k6.io"))
    sleep(100.millis)

    val r2 = http.batch(
      Seq[BatchRequest](
        "https://test.k6.io",
        url"https://test.k6.io/user/$userId",
        ObjectBatchRequest("POST", "https://test.k6.io/contacts.php", body = Some("body")),
        ObjectBatchRequest("PUT", "https://test.k6.io/data", body = Some("data"))
      )
    )
    sleep(100.millis)

    val r3 = http.batch(Map("home" -> "https://test.k6.io"))
    sleep(100.millis)

    val r4 = http.batch(
      Map[String, BatchRequest](
        "str" -> "https://test.k6.io",
        "url" -> url"https://test.k6.io/user/$userId",
        "get" -> ObjectBatchRequest("GET", "https://test.k6.io/about"),
        "post" -> ObjectBatchRequest("POST", "https://test.k6.io/api", body = Some("""{"k":"v"}"""))
      )
    )
    sleep(100.millis)

    val all = r1 ++ r2 ++ r3.values.toSeq ++ r4.values.toSeq
    all.foreach(r =>
      check(r, Checkers("ok" -> Check[Response](res => res.status >= 200 && res.status < 400)))
    )
    println(s"Total: ${all.length}")
  }

  @JSExportTopLevel("options")
  val options = Options(vus = Some(1), iterations = Some(1))
}
