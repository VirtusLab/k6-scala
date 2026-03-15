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

object JsonResponseExample {
  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    val response = http.post(
      "https://quickpizza.grafana.com/api/pizza",
      body = Some(
        """{"maxCaloriesPerSlice":500,"mustBeVegetarian":false,"excludedIngredients":[],"excludedTools":[],"maxNumberOfToppings":5,"minNumberOfToppings":2,"customName":""}"""
      ),
      params = Some(
        Params(
          headers = Some(Map("Content-Type" -> "application/json", "accept" -> "application/json")),
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
        "status is 200" -> Check[Response](_.status == 200),
        "json() parses body" -> Check[Response](r => !js.isUndefined(r.json())),
        "json selector extracts field" -> Check[Response] { r =>
          val pizza = r.json("pizza")
          !js.isUndefined(pizza) && pizza != null
        }
      )
    )

    val timings = response.timings
    println(s"Request duration: ${timings.duration}ms")
    println(s"  Blocked:        ${timings.blocked}ms")
    println(s"  Connecting:     ${timings.connecting}ms")
    println(s"  TLS handshake:  ${timings.tls_handshaking}ms")
    println(s"  Sending:        ${timings.sending}ms")
    println(s"  Waiting (TTFB): ${timings.waiting}ms")
    println(s"  Receiving:      ${timings.receiving}ms")

    // json() on a non-JSON response would throw a k6 runtime error.
    // json(selector) returns null for a missing path — not Option.
    val missing = response.json("nonexistent.path")
    println(s"Missing path result is null: ${missing == null}")

    sleep(100.millis)
  }

  @JSExportTopLevel("options")
  val options = Options(vus = Some(1), iterations = Some(1))
}
