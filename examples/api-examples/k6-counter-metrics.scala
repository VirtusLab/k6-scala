//> using scala "3.5.0"
//> using jsVersion "1.18.1"
//> using platform scala-js
//> using dep "org.virtuslab::k6-scala::dev"
package example

import scala.scalajs.js
import scala.scalajs.js.annotation._
import scala.scalajs.js.JSConverters._
import org.virtuslab.scalajs.k6._
import org.virtuslab.scalajs.k6.http._
import org.virtuslab.scalajs.k6.metrics._
import org.virtuslab.scalajs.k6.options._
import scala.concurrent.duration._

/**
 * E2E example: k6/metrics Counter. Creates a counter, adds values with and without tags,
 * and defines a threshold so k6 output includes the metric and validates it.
 *
 * Edge cases (documented for reference):
 * - Counter with empty name: k6 runtime behaviour is implementation-specific.
 * - Adding 0: succeeds without error.
 * - Very large values (e.g. Double.MaxValue): k6 may have limits; check k6 docs for your version.
 */
object CounterMetricsExample {

  val myCounter = Counter("my_counter")

  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    myCounter.add(1)
    myCounter.add(1, Some(Map("tag1" -> "value1", "tag2" -> "value2")))
    myCounter.add(2)
    myCounter.add(0) // edge case: adding 0 is valid
    val success = true
    myCounter.add(success, None) // convenience: true => 1, false => 0

    val res = http.get("https://test.k6.io")
    myCounter.add(if (res.status >= 400) 1 else 0, Some(Map("kind" -> "http_error")))

    sleep(500.millis)
  }

  @JSExportTopLevel("options")
  val options: Options = Options(
    vus = Some(1),
    iterations = Some(1),
    thresholds = Some(
      Map("my_counter" -> Seq("count < 500").toJSArray).toJSDictionary
        .asInstanceOf[js.Dictionary[js.Array[String | ObjectThreshold]]]
    )
  )
}
