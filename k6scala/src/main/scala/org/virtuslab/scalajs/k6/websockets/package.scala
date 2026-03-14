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

import org.virtuslab.scalajs.k6.websockets.{MessageEvent, ReadyState, WebSocket, WebSocketParams}

package object websockets {

  /**
   * Extension methods for [[WebSocket]] for more idiomatic use. The WebSocket class already
   * exposes `onOpen`, `onMessage`, etc.; this implicit class can be used for additional helpers
   * (e.g. checking readyState against [[ReadyState]]).
   */
  implicit class WebSocketOps(private val ws: WebSocket) extends AnyVal {

    /** True if the connection is open. */
    def isOpen: Boolean = ws.readyState == ReadyState.Open

    /** True if the connection is closed or closing. */
    def isClosed: Boolean =
      ws.readyState == ReadyState.Closed || ws.readyState == ReadyState.Closing
  }
}
