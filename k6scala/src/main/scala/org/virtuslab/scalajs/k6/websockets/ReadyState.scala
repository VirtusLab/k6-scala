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

/**
 * WebSocket connection state (browser standard). Values match
 * [[https://developer.mozilla.org/en-US/docs/Web/API/WebSocket/readyState WebSocket.readyState]].
 */
sealed abstract class ReadyState(val value: Int)

object ReadyState {

  /** Connection is being established. */
  case object Connecting extends ReadyState(0)

  /** Connection is open and ready. */
  case object Open extends ReadyState(1)

  /** Connection is in the process of closing. */
  case object Closing extends ReadyState(2)

  /** Connection is closed or could not be opened. */
  case object Closed extends ReadyState(3)

  def fromInt(n: Int): ReadyState =
    n match {
      case 0 => Connecting
      case 1 => Open
      case 2 => Closing
      case 3 => Closed
      case _ => Closed
    }
}
