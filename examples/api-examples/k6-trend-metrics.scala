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
 * E2E example: k6/metrics Trend.
 *
 * Creates a trend metric for tracking response durations, adds samples from HTTP timings and
 * via the FiniteDuration overload, and defines a threshold so k6 output includes the metric
 * and validates the 95th percentile.
 *
 * Edge cases (k6 runtime behaviour):
 *   - Adding zero is valid (0ms is a legitimate timing).
 *   - Negative values are accepted but semantically meaningless for timings.
 *   - Threshold variables (avg, min, max, med, p(N)) are reported in milliseconds.
 */
object TrendMetricsExample {

  private val apiDuration = Trend("api_duration", isTime = true)

  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    val res = http.get("https://test.k6.io")

    // Record total response duration from HTTP timings (milliseconds).
    apiDuration.add(res.timings.duration)

    // Tagged sample for a specific phase of the request.
    apiDuration.add(res.timings.waiting, Some(Map("phase" -> "waiting")))

    // FiniteDuration convenience overload: add 200ms.
    apiDuration.add(200.millis)

    sleep(50.millis)
  }

  @JSExportTopLevel("options")
  val options: Options = Options(
    vus = Some(1),
    iterations = Some(5),
    thresholds = Some(
      Map("api_duration" -> Seq("p(95) < 500").toJSArray).toJSDictionary
        .asInstanceOf[js.Dictionary[js.Array[String | ObjectThreshold]]]
    )
  )
}
