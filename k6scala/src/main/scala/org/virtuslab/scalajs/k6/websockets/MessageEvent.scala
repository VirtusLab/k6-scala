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

/**
 * Native facade for the event object passed to `onmessage` and `addEventListener("message", ...)`.
 * Follows the browser [[https://developer.mozilla.org/en-US/docs/Web/API/MessageEvent MessageEvent]]
 * shape. The `data` property is a string for text frames, or (depending on `binaryType`) an
 * ArrayBuffer or Blob for binary frames.
 */
@js.native
trait MessageEvent extends js.Object {

  /** Message payload. String for text; ArrayBuffer or Blob for binary (see WebSocket.binaryType). */
  def data: js.Any = js.native
}
