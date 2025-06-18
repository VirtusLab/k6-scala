//> using scala "3.5.0"
//> using jsVersion "1.18.1"
//> using platform scala-js
//> using dep "org.scalajs::k6-scala::0.0.1-SNAPSHOT"
package example

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import com.github.wiacekm.scalajs.k6.http.*
import com.github.wiacekm.scalajs.k6.http.HttpURL.*
import com.github.wiacekm.scalajs.k6.*
import com.github.wiacekm.scalajs.k6.options.*
import com.github.wiacekm.scalajs.k6.options.Scenario.*
import scala.concurrent.duration.*

object SingleRequest {
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
  @JSExportTopLevel("options")
  val options = Options(
    scenarios = Some(
      Map(
        "test" -> RampingVusScenario(
          startVUs = Some(3),
          stages = List(
            Stage(target = 20, duration = 30.seconds), // linearly go from 3 VUs to 20 VUs for 30s
            Stage(target = 100, duration = 0.second), // instantly jump to 100 VUs
            Stage(target = 100, duration = 10.minutes) // continue with 100 VUs for 10 minutes
          )
        )
      )
    )
  )
}
