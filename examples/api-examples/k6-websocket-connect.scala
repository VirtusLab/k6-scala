//> using scala "3.5.0"
//> using jsVersion "1.20.2"
//> using platform scala-js
//> using dep "org.virtuslab::k6-scala::dev"
package example

import scala.scalajs.js.annotation.*
import org.virtuslab.scalajs.k6.*
import org.virtuslab.scalajs.k6.http.*
import org.virtuslab.scalajs.k6.ws.*
import org.virtuslab.scalajs.k6.options.*

/**
 * E2E example for k6/ws connect() and WsParams.
 *
 * Connects to a WebSocket echo server, closes the socket from the callback (so the VU unblocks),
 * and verifies the handshake response status is 101 (Switching Protocols).
 *
 * Edge cases (documented): connection to non-existent host will eventually time out; empty params
 * use k6 defaults.
 */
object WebSocketConnect {
  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    val url = "wss://echo.websocket.org"
    val res = ws.connect(url, None) { socket =>
      socket.close()
    }
    check(
      res,
      Checkers("status is 101" -> Check[Response](r => r.status == 101))
    )
  }

  @JSExportTopLevel("options")
  val options = Options(vus = Some(1), iterations = Some(1))
}
