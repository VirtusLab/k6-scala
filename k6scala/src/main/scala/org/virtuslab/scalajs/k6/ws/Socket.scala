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

package org.virtuslab.scalajs.k6.ws

import scala.scalajs.js
import scala.scalajs.js.typedarray.ArrayBuffer

/**
 * Native facade for the k6/ws Socket object passed to the `ws.connect()` callback.
 *
 * Provides event handlers (`on`), `send`/`sendBinary`, `ping`, `close`, and `setInterval`/
 * `setTimeout`. The Socket represents the WebSocket connection; the VU is blocked until the
 * connection is closed. For typed event handlers and `FiniteDuration`-based timers, use the
 * extension methods from the package object (e.g. `onOpen`, `onMessage`, `setTimeout(duration)`).
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-ws/socket/ k6 Socket]]
 */
@js.native
trait Socket extends js.Object {

  /**
   * Registers an event listener. Events: "open", "message", "binaryMessage", "ping", "pong",
   * "close", "error". Multiple handlers for the same event all fire. Prefer typed wrappers
   * (onOpen, onMessage, etc.) from the package object.
   */
  def on(event: String, callback: js.Function): Unit = js.native

  /** Sends a text message to the server. */
  def send(data: String): Unit = js.native

  /** Sends binary data to the server. */
  def sendBinary(data: ArrayBuffer): Unit = js.native

  /** Sends a WebSocket ping frame. */
  def ping(): Unit = js.native

  /** Closes the WebSocket connection. Triggers the "close" event and unblocks the VU. */
  def close(): Unit = js.native

  /**
   * Calls the callback repeatedly at the given interval (ms) while the connection is open.
   * Interval is in milliseconds.
   */
  def setInterval(callback: js.Function, interval: Double): Unit = js.native

  /**
   * Calls the callback once after the given delay (ms) if the connection is still open.
   * Delay is in milliseconds.
   */
  def setTimeout(callback: js.Function, period: Double): Unit = js.native
}
