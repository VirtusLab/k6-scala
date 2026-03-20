//> using scala "3.5.0"
//> using platform "scala-js"
//> using jsModuleKind "esmodule"
//> using jsEmitSourceMaps true
//> using jsNoOpt true
//> using dep "org.virtuslab::k6-jslib::dev"
//> using dep "org.virtuslab::k6-scala::dev"

import scala.scalajs.js.annotation._

import org.virtuslab.scalajs.k6._
import org.virtuslab.scalajs.k6.jslib.chaijs.ChaiJS
import org.virtuslab.scalajs.k6.options.Options

@JSExportTopLevel("options")
val options: Options = Options(vus = Some(1), iterations = Some(1))

@JSExportTopLevel(JSImport.Default)
def main(): Unit = {
  // Mirrors https://grafana.com/docs/k6/latest/javascript-api/jslib/k6chaijs/config/
  ChaiJS.config.truncateVariableThreshold = 20
  ChaiJS.config.truncateMsgThreshold = 300
  ChaiJS.config.aggregateChecks = false
  ChaiJS.config.logFailures = true

  ChaiJS.describe("k6-chaijs BDD assertions") {
    ChaiJS.describe("equality") {
      ChaiJS.expect(2).toEqual(2)
      ChaiJS.expect("hello").toEqual("hello")
    }

    ChaiJS.describe("numeric range (within)") {
      ChaiJS.expect(5).toBeWithin(1, 10)
      ChaiJS.expect(5.5).toBeWithin(5.0, 6.0)
    }

    ChaiJS.describe("HTTP-style status codes") {
      val status = 200
      ChaiJS.expect(status).toEqual(200)
      ChaiJS.expect(status.toDouble).toBeWithin(200.0, 299.0)
    }
  }
}
