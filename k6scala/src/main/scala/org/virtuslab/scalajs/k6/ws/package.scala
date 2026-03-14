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

import scala.scalajs.js.JSConverters._
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
}
