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

/**
 * Minimal native facade for the k6/ws Socket object passed to the `ws.connect()` callback.
 *
 * This trait is extended in Module 3.2 with event handlers (`on`), `send`, `sendBinary`, `ping`,
 * `setInterval`, and `setTimeout`. The Socket represents the WebSocket connection; the VU is
 * blocked until the connection is closed.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-ws/socket/ k6 Socket]]
 */
@js.native
trait Socket extends js.Object {

  /** Closes the WebSocket connection. Triggers the "close" event and unblocks the VU. */
  def close(): Unit = js.native
}
