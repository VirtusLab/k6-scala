//> using scala "3.5.0"
//> using platform "scala-js"
//> using jsModuleKind "esmodule"
//> using jsEmitSourceMaps true
//> using jsNoOpt true
//> using dep "org.virtuslab::k6-scala::dev"

import scala.scalajs.js
import scala.scalajs.js.annotation._

import org.virtuslab.scalajs.k6._
import org.virtuslab.scalajs.k6.http._
import org.virtuslab.scalajs.k6.options._
import org.virtuslab.scalajs.k6.jslib._

@JSExportTopLevel("options")
val options: Options =
  Options(vus = Some(1), duration = Some("1s"))

@JSExportTopLevel(JSImport.Default)
def main(): Unit = {
  // Placeholder example that only verifies that `k6-jslib` can be imported and linked
  val url = "https://test.k6.io"
  val response = Http.get(url)

  check(
    response,
    Map(
      "status is 200" -> ((r: Response) => r.status == 200)
    )
  )
}

