//> using scala "3.5.0"
//> using jsVersion "1.20.2"
//> using platform scala-js
//> using dep "org.virtuslab::k6-scala::dev"
package example

import scala.concurrent.duration._
import scala.scalajs.js.annotation._
import org.virtuslab.scalajs.k6._
import org.virtuslab.scalajs.k6.http._
import org.virtuslab.scalajs.k6.ws._
import org.virtuslab.scalajs.k6.options._

/**
 * E2E example for k6/ws Socket: event handlers (onOpen, onMessage, onClose), send, setInterval,
 * and setTimeout.
 *
 * Connects to a WebSocket echo server, registers open/message/close handlers, sends a message on
 * open, pings every 1s via setInterval, and closes the socket after 2s via setTimeout. Verifies
 * handshake status 101.
 *
 * Edge cases (documented): calling send() after close() is undefined in k6; multiple handlers for
 * the same event all fire; setInterval with 0ms is allowed but may behave as minimal delay.
 */
object WebSockets {
  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    val url = "wss://echo.websocket.org"
    val res = ws.connect(url, None) { socket =>
      socket.onOpen { () =>
        socket.send("hello")
        socket.scheduleInterval(() => socket.ping(), 1.second)
      }
      socket.onMessage { _ => () }
      socket.onClose { () => () }
      socket.onError { _ => () }
      socket.scheduleTimeout(() => socket.close(), 2.seconds)
    }
    check(
      res,
      Checkers("status is 101" -> Check[Response](r => r.status == 101))
    )
  }

  @JSExportTopLevel("options")
  val options = Options(vus = Some(1), iterations = Some(1))
}
