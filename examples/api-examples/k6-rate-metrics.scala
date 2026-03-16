//> using scala "3.5.0"
//> using jsVersion "1.18.1"
//> using platform scala-js
//> using dep "org.virtuslab::k6-scala::dev"

package example

import scala.concurrent.duration._
import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation._

import org.virtuslab.scalajs.k6._
import org.virtuslab.scalajs.k6.http._
import org.virtuslab.scalajs.k6.metrics._
import org.virtuslab.scalajs.k6.options._

/**
 * E2E example: k6/metrics Rate.
 *
 * Creates a rate metric that tracks the fraction of failed HTTP requests based on status code and
 * defines a threshold so k6 output includes the metric and validates the error rate.
 *
 * Behaviour notes (k6 runtime):
 *   - Any non-zero value is treated as a "hit".
 *   - The resulting `rate` value is between 0.0 and 1.0.
 *   - Threshold expressions use `rate` (e.g. `rate < 0.1` for less than 10% failures).
 */
object RateMetricsExample {

  private val errorRate = Rate("error_rate")

  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    val res = http.get("https://test.k6.io")

    // Tagged sample for a specific endpoint.
    errorRate.add(res.status >= 400, Some(Map("endpoint" -> "/")))

    // Explicit numeric usage: 1 for failure, 0 for success.
    val numericSample = if (res.status >= 400) 1 else 0
    errorRate.add(numericSample, Some(Map("kind" -> "explicit_numeric")))

    sleep(50.millis)
  }

  @JSExportTopLevel("options")
  val options: Options = Options(
    vus = Some(1),
    iterations = Some(5),
    thresholds = Some(
      Map("error_rate" -> Seq("rate < 0.1").toJSArray).toJSDictionary
        .asInstanceOf[js.Dictionary[js.Array[String | ObjectThreshold]]]
    )
  )
}
