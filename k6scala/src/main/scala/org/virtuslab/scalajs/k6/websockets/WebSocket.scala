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

package org.virtuslab.scalajs.k6.websockets

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.typedarray.ArrayBuffer

/**
 * Native facade for the k6/websockets WebSocket class (browser-standard API with k6 extensions).
 * Uses a global event loop so a single VU can have multiple concurrent connections.
 *
 * @see
 *   [[https://k6.io/docs/javascript-api/k6-websockets/ k6 websockets]]
 */
@js.native
@JSImport("k6/websockets", "WebSocket")
private[websockets] class WebSocketNative(
    urlArg: String,
    protocols: js.UndefOr[js.Array[String]] = js.undefined,
    params: js.UndefOr[WebSocketParams] = js.undefined
) extends js.Object {

  /** Current connection state: 0 CONNECTING, 1 OPEN, 2 CLOSING, 3 CLOSED. */
  def readyState: Int = js.native

  /** The URL of the connection as resolved by the constructor. */
  def url: String = js.native

  /** Number of bytes of data queued by send() but not yet transmitted. */
  def bufferedAmount: Double = js.native

  /**
   * Type of binary data received: `"blob"` or `"arraybuffer"`. Default is `"blob"`.
   * Assign to control the type of `MessageEvent.data` for binary frames.
   */
  var binaryType: String = js.native

  var onopen: js.UndefOr[js.Function0[Unit]] = js.native
  var onmessage: js.UndefOr[js.Function1[MessageEvent, Unit]] = js.native
  var onerror: js.UndefOr[js.Function1[js.Any, Unit]] = js.native
  var onclose: js.UndefOr[js.Function0[Unit]] = js.native
  var onping: js.UndefOr[js.Function0[Unit]] = js.native
  var onpong: js.UndefOr[js.Function0[Unit]] = js.native

  /** Sends data (string, ArrayBuffer, Blob, or ArrayBufferView). */
  def send(data: js.Any): Unit = js.native

  /** Closes the connection. Optional code (default 1000 = normal closure). */
  def close(code: js.UndefOr[Int] = js.undefined): Unit = js.native

  /** Sends a ping frame (k6-specific extension). */
  def ping(): Unit = js.native

  /** Adds an event listener. Events: "open", "message", "ping", "pong", "close", "error". */
  def addEventListener(event: String, handler: js.Function): Unit = js.native
}

/**
 * Scala wrapper for the k6/websockets WebSocket. Idiomatic constructors and functional-style
 * event handlers; use [[WebSocketOps]] in the package object for `onOpen`, `onMessage`, etc.
 */
final class WebSocket private (private val inner: WebSocketNative) {

  def readyState: ReadyState = ReadyState.fromInt(inner.readyState)
  def url: String = inner.url
  def bufferedAmount: Double = inner.bufferedAmount
  def binaryType_=(value: String): Unit = inner.binaryType = value
  def binaryType: String = inner.binaryType

  def onOpen(handler: () => Unit): Unit =
    inner.onopen = js.Any.fromFunction0(handler)

  def onMessage(handler: MessageEvent => Unit): Unit =
    inner.onmessage = js.Any.fromFunction1(handler)

  def onError(handler: js.Any => Unit): Unit =
    inner.onerror = js.Any.fromFunction1(handler)

  def onClose(handler: () => Unit): Unit =
    inner.onclose = js.Any.fromFunction0(handler)

  def onPing(handler: () => Unit): Unit =
    inner.onping = js.Any.fromFunction0(handler)

  def onPong(handler: () => Unit): Unit =
    inner.onpong = js.Any.fromFunction0(handler)

  /** Sends text or binary data. Use JSON.stringify in JS for objects; in Scala, pass a String. */
  def send(data: String): Unit =
    inner.send(data)

  /** Sends binary data. */
  def sendBinary(data: ArrayBuffer): Unit =
    inner.send(data)

  /** Closes the connection with optional code (default 1000). */
  def close(code: Option[Int] = None): Unit =
    inner.close(code.orUndefined)

  /** Sends a ping frame. */
  def ping(): Unit =
    inner.ping()

  /** Adds an event listener (e.g. "open", "message", "close", "error", "ping", "pong"). */
  def addEventListener(event: String, handler: js.Function): Unit =
    inner.addEventListener(event, handler)
}

object WebSocket {

  /** Creates a new WebSocket connection. Protocols are reserved for future use in k6. */
  def apply(url: String): WebSocket =
    new WebSocket(new WebSocketNative(urlArg = url))

  /** Creates a new WebSocket connection with k6 params (headers, tags, compression, jar). */
  def apply(url: String, params: WebSocketParams): WebSocket =
    new WebSocket(new WebSocketNative(urlArg = url, params = params))

  /** Creates a new WebSocket with optional protocols and params (protocols not yet implemented in k6). */
  def apply(
      url: String,
      protocols: Option[Seq[String]],
      params: Option[WebSocketParams]
  ): WebSocket = {
    val proto = protocols.map(_.toJSArray).orUndefined
    val p = params.orUndefined
    new WebSocket(new WebSocketNative(urlArg = url, protocols = proto, params = p))
  }
}
