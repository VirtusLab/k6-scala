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

package org.virtuslab.scalajs.k6.grpc

/**
 * Event names for [[Stream.on]]. Use the typed wrappers [[Stream.onData]], [[Stream.onError]],
 * [[Stream.onEnd]] instead of passing these strings to [[Stream.on]].
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-net-grpc/stream/stream-on/ Stream.on()]]
 */
sealed trait StreamEvent {
  def name: String
}

object StreamEvent {

  /** Emitted when the server sends data. Handler receives (message[, metadata]). */
  case object Data extends StreamEvent { val name: String = "data" }

  /** Emitted when an error occurs; the stream is closed. Handler receives the error object. */
  case object Error extends StreamEvent { val name: String = "error" }

  /** Emitted when the server closes the incoming stream. */
  case object End extends StreamEvent { val name: String = "end" }
}
