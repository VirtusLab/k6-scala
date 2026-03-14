//> using scala "3.5.0"
//> using jsVersion "1.20.2"
//> using platform scala-js
//> using dep "org.virtuslab::k6-scala::dev"
package example

import scala.scalajs.js.annotation._
import scala.concurrent.duration._
import org.virtuslab.scalajs.k6._
import org.virtuslab.scalajs.k6.Checkers
import org.virtuslab.scalajs.k6.Check
import org.virtuslab.scalajs.k6.websockets._
import org.virtuslab.scalajs.k6.timers._
import org.virtuslab.scalajs.k6.options._

/**
 * E2E example for k6/websockets (modern WebSocket API): multiple concurrent connections per VU,
 * event handlers (onOpen, onMessage, onClose), send/receive, readyState checks, close with code.
 *
 * Creates two WebSocket connections to an echo server, sends a message on open, closes with code
 * 1000 after a delay via setTimeout. Verifies readyState is Open in onopen and Closed in onclose.
 *
 * Edge cases: creating WebSocket in VU code (not init) works; close(1000) uses normal closure;
 * binaryType can be set to "arraybuffer" for binary frames (default "blob").
 */
object WebSocketsModern {
  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    val url = "wss://echo.websocket.org"

    def openOne(id: Int): Unit = {
      val ws = WebSocket(url)
      ws.binaryType = "arraybuffer"
      ws.onOpen { () =>
        check(
          ws.readyState == ReadyState.Open,
          Checkers("readyState is Open" -> Check[Boolean](identity))
        )
        ws.send(s"hello-$id")
        setTimeout(() => ws.close(Some(1000)), 2.seconds)
      }
      ws.onMessage { _ => () }
      ws.onClose { () =>
        check(
          ws.readyState == ReadyState.Closed,
          Checkers("readyState is Closed" -> Check[Boolean](identity))
        )
      }
      ws.onError { _ => () }
    }

    openOne(1)
    openOne(2)
  }

  @JSExportTopLevel("options")
  val options = Options(vus = Some(1), iterations = Some(1))
}
