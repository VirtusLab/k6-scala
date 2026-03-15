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
 * E2E example: k6/metrics Gauge.
 *
 * Creates a gauge, records increasing and decreasing values, and defines a threshold so that
 * k6 output includes the metric and validates the final value.
 *
 * Edge cases (k6 runtime behaviour):
 *   - Negative values are allowed and treated as regular numbers.
 *   - Threshold expressions use `value` (e.g. `value < 100`).
 */
object GaugeMetricsExample {

  val queueGauge = Gauge("queue_length")

  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    // Initial queue size
    queueGauge.add(10)

    // Queue grows
    queueGauge.add(25, Some(Map("phase" -> "warmup")))

    // Queue shrinks again
    queueGauge.add(5, Some(Map("phase" -> "steady_state")))

    // Negative value edge case (e.g. adjustment below baseline)
    queueGauge.add(-1, Some(Map("phase" -> "underflow_test")))

    val res = http.get("https://test.k6.io")
    // Record status code as a gauge value with tag
    queueGauge.add(res.status.toDouble, Some(Map("kind" -> "status_code")))

    sleep(50.millis)
  }

  @JSExportTopLevel("options")
  val options: Options = Options(
    vus = Some(1),
    iterations = Some(3),
    thresholds = Some(
      Map("queue_length" -> Seq("value < 500").toJSArray).toJSDictionary
        .asInstanceOf[js.Dictionary[js.Array[String | ObjectThreshold]]]
    )
  )
}
