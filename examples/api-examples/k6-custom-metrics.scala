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
 * E2E example: unified custom metrics.
 *
 * Demonstrates the combined use of Counter, Gauge, Rate, and Trend metrics in a single script,
 * all imported via `org.virtuslab.scalajs.k6.metrics._`. Each metric records samples and has a
 * corresponding threshold so k6 output includes the metric and validates its value.
 */
object CustomMetricsExample {

  private val requestCount = Counter("custom_request_count")
  private val queueGauge = Gauge("custom_queue_length")
  private val errorRate = Rate("custom_error_rate")
  private val latencyTrend = Trend("custom_latency", isTime = true)

  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    val res = http.get("https://test.k6.io")

    // Counter: track total requests and failures.
    requestCount.add(1)
    requestCount.add(res.status >= 400, Some(Map("kind" -> "failure")))

    // Gauge: track an artificial queue length that can go up and down.
    queueGauge.add(10)
    queueGauge.add(3, Some(Map("phase" -> "after_response")))

    // Rate: track fraction of failed requests.
    errorRate.add(res.status >= 400)
    errorRate.add(res.status >= 400, Some(Map("endpoint" -> "/")))

    // Trend: record response duration and a synthetic processing step.
    latencyTrend.add(res.timings.duration)
    latencyTrend.add(150.millis)

    sleep(50.millis)
  }

  @JSExportTopLevel("options")
  val options: Options = Options(
    vus = Some(1),
    iterations = Some(5),
    thresholds = Some(
      Map(
        "custom_request_count" -> Seq("count < 1_000").toJSArray,
        "custom_queue_length" -> Seq("value < 1_000").toJSArray,
        "custom_error_rate" -> Seq("rate < 0.5").toJSArray,
        "custom_latency" -> Seq("p(95) < 1_000").toJSArray
      ).toJSDictionary
        .asInstanceOf[js.Dictionary[js.Array[String | ObjectThreshold]]]
    )
  )
}
