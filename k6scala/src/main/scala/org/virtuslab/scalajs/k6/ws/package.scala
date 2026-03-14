/*
 * Copyright 2024 VirtusLab
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.virtuslab.scalajs.k6

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.typedarray.ArrayBuffer
import org.virtuslab.scalajs.k6.http.Response
import org.virtuslab.scalajs.k6.ws.WSNative
import org.virtuslab.scalajs.k6.ws.WsParams
import org.virtuslab.scalajs.k6.ws.Socket

package object ws {

  /**
   * Initiates a WebSocket connection to the given URL. Blocks the VU until the connection is
   * closed. The callback receives the [[Socket]] for event handlers and sending/receiving
   * messages.
   *
   * Connection to a non-existent host will eventually time out (or fail according to params);
   * empty params use k6 defaults.
   *
   * @param url
   *   WebSocket URL (e.g. `"wss://echo.websocket.org"`).
   * @param params
   *   Optional connection parameters; `None` uses defaults.
   * @param callback
   *   Function called when the connection is initiated; receives the [[Socket]].
   * @return
   *   HTTP Response from the WebSocket handshake.
   */
  def connect(
      url: String,
      params: Option[WsParams] = None
  )(callback: Socket => Unit): Response =
    WSNative.connect(url, params.orUndefined, callback)

  /**
   * Extension methods for [[Socket]] providing typed event handlers and `FiniteDuration`-based
   * timers. Use these instead of raw `on(event, callback)`; use `scheduleInterval`/`scheduleTimeout`
   * instead of native `setInterval`/`setTimeout` when you have Scala durations.
   */
  implicit class SocketOps(private val socket: Socket) extends AnyVal {

    def onOpen(callback: () => Unit): Unit =
      socket.on("open", js.Any.fromFunction0(callback))

    def onMessage(callback: String => Unit): Unit =
      socket.on("message", js.Any.fromFunction1(callback))

    def onBinaryMessage(callback: ArrayBuffer => Unit): Unit =
      socket.on("binaryMessage", js.Any.fromFunction1(callback))

    def onPing(callback: () => Unit): Unit =
      socket.on("ping", js.Any.fromFunction0(callback))

    def onPong(callback: () => Unit): Unit =
      socket.on("pong", js.Any.fromFunction0(callback))

    def onClose(callback: () => Unit): Unit =
      socket.on("close", js.Any.fromFunction0(callback))

    def onError(callback: js.Object => Unit): Unit =
      socket.on("error", js.Any.fromFunction1(callback))

    /**
     * Schedules the callback to run repeatedly at the given interval while the connection is open.
     * Idiomatic alternative to native `setInterval(callback, ms)` which takes milliseconds as Double.
     * Calling send() after close() is undefined in k6 — avoid doing so.
     */
    def scheduleInterval(callback: () => Unit, interval: FiniteDuration): Unit =
      socket.setInterval(js.Any.fromFunction0(callback), interval.toMillis.toDouble)

    /**
     * Schedules the callback to run once after the given period if the connection is still open.
     * Idiomatic alternative to native `setTimeout(callback, ms)` which takes milliseconds as Double.
     */
    def scheduleTimeout(callback: () => Unit, period: FiniteDuration): Unit =
      socket.setTimeout(js.Any.fromFunction0(callback), period.toMillis.toDouble)
  }
}
