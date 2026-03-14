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
import scala.scalajs.js.annotation.JSImport
import org.virtuslab.scalajs.k6.http.Response

@js.native
@JSImport("k6/ws", JSImport.Namespace)
private[ws] object WSNative extends js.Object {

  /**
   * Initiates a WebSocket connection. Blocks the VU until the connection is closed (e.g. by
   * `socket.close()`, remote close, or VU interruption). The callback receives the [[Socket]]
   * for setting up event handlers and sending/receiving messages.
   *
   * @param url
   *   WebSocket URL (e.g. `"wss://echo.websocket.org"`).
   * @param params
   *   Optional connection parameters (headers, tags, compression, jar, timeout).
   * @param callback
   *   Function called when the connection is initiated; receives the [[Socket]].
   * @return
   *   HTTP Response object from the WebSocket handshake.
   */
  def connect(
      url: String,
      params: js.UndefOr[WsParams],
      callback: js.Function1[Socket, Unit]
  ): Response =
    js.native
}
