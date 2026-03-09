//> using scala "3.5.0"
//> using jsVersion "1.20.2"
//> using platform scala-js
//> using dep "org.virtuslab::k6-scala::dev"
package example

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import org.virtuslab.scalajs.k6.http.*
import org.virtuslab.scalajs.k6.http.HttpURL.*
import org.virtuslab.scalajs.k6.http.HttpMethod.*
import org.virtuslab.scalajs.k6.*
import org.virtuslab.scalajs.k6.options.*
import scala.concurrent.duration.*

object RequestExample {
  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    val userId = "123"
    val base = "https://quickpizza.grafana.com"

    val r1 = http.request(GET, base)
    sleep(100.millis)

    val r2 = http.request(
      POST,
      url"$base/api/pizza",
      body = Some(
        """{"maxCaloriesPerSlice":1000,"mustBeVegetarian":false,"excludedIngredients":[],"excludedTools":[],"maxNumberOfToppings":5,"minNumberOfToppings":2,"customName":""}"""
      )
    )
    sleep(100.millis)

    val r3 = http.request(POST, url"$base/api/pizza", body = None)

    val all = Seq(r1, r2, r3)
    all.foreach(r =>
      check(r, Checkers("ok" -> Check[Response](res => res.status >= 200 && res.status < 500)))
    )
    println(s"Total: ${all.length}")
  }

  @JSExportTopLevel("options")
  val options = Options(vus = Some(1), iterations = Some(1))
}
