//> using scala "3.5.0"
//> using jsVersion "1.18.1"
//> using platform scala-js
//> using dep "org.virtuslab::k6-scala::dev"
package example

import scala.scalajs.js
import scala.scalajs.js.annotation._
import scala.scalajs.js.JSConverters._
import org.virtuslab.scalajs.k6._
import org.virtuslab.scalajs.k6.grpc._
import org.virtuslab.scalajs.k6.options._
import scala.concurrent.duration._

/**
 * E2E example: k6/net/grpc Client — load proto, connect, invoke unary RPC, check response status.
 *
 * Uses the public Grafana QuickPizza gRPC demo service. You need `quickpizza.proto` in the same
 * directory as this script (or set import paths). Download from:
 * https://raw.githubusercontent.com/grafana/quickpizza/refs/heads/main/proto/quickpizza.proto
 *
 * Edge cases (documented):
 * - Invoke before connect: k6 throws a runtime error; always call connect() before invoke().
 * - Connect to unreachable host: connection will eventually timeout (see connect params timeout).
 */
object GrpcExample {

  val client = Client()

  // Load proto during init (must be in init phase). Use None for default import paths.
  client.load(None, "quickpizza.proto")

  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    client.connect("grpc-quickpizza.grafana.com:443", None)

    val request = js.Dynamic.literal(
      ingredients = js.Array("Cheese", "Tomatoes"),
      dough = "Thin"
    )
    val response = client.invoke("quickpizza.GRPC/RatePizza", request, None)
    check(
      response,
      Checkers(
        "status is OK" -> Check[GrpcResponse](r => r.status == StatusOK),
        "has stars rating" -> Check[GrpcResponse](r =>
          r.status == StatusOK && {
            val msg = r.message.asInstanceOf[js.Dynamic]
            msg.starsRating != js.undefined
          }
        )
      )
    )

    client.close()
    sleep(1.second)
  }

  @JSExportTopLevel("options")
  val options: Options = Options(vus = Some(1), iterations = Some(1))
}
