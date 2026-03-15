//> using scala "3.5.0"
//> using jsVersion "1.18.1"
//> using platform scala-js
//> using dep "org.virtuslab::k6-scala::dev"
package example

import scala.scalajs.js
import scala.scalajs.js.annotation._
import org.virtuslab.scalajs.k6._
import org.virtuslab.scalajs.k6.grpc._
import org.virtuslab.scalajs.k6.options._
import scala.concurrent.duration._

/**
 * E2E example: k6/net/grpc Stream — client/server/bidirectional streaming.
 *
 * Requires a gRPC server that exposes a streaming method (e.g. k6's RouteGuide demo).
 *
 * Get route_guide.proto from the k6 repo:
 *   https://raw.githubusercontent.com/grafana/k6/master/examples/grpc_server/route_guide.proto
 * Place it in this directory (examples/api-examples/) or set import paths in client.load().
 *
 * Run the demo server (from k6 repo root, requires Go):
 *   go run -mod=mod examples/grpc_server/\*.go
 * Then run: k6 run k6-grpc-streaming.js (or use -e GRPC_ADDR=host:port if needed).
 *
 * Edge cases (documented):
 * - Write after end: avoid; behavior is undefined in k6.
 * - Multiple handlers for the same event: all fire.
 */
object GrpcStreamingExample {

  val client = Client()
  // Load route_guide.proto (download from k6 repo URL above).
  client.load(None, "route_guide.proto")

  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    // Use k6 -e GRPC_ADDR=host:port to override; default matches k6 grpc_server demo.
    client.connect("127.0.0.1:10000", Some(GrpcConnectParams(plaintext = Some(true))))

    val stream = Stream(client, "main.RouteGuide/RecordRoute", None)

    stream.onData((msg: js.Any) => {
      val m = msg.asInstanceOf[js.Dynamic]
      if (m.pointCount != js.undefined)
        println(s"Trip: ${m.pointCount} points, ${m.distance} m")
    })
    stream.onError((err: js.Object) => println("Stream error: " + err))
    stream.onEnd(() => {
      client.close()
      println("Stream ended")
    })

    // Send one point (can loop to send more for client/bidirectional streaming).
    stream.write(js.Dynamic.literal(latitude = 406109563, longitude = -742186778))
    stream.end()

    sleep(1.second)
  }

  @JSExportTopLevel("options")
  val options: Options = Options(vus = Some(1), iterations = Some(1))
}
