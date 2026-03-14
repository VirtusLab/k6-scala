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

/**
 * Event types for [[Socket]] handlers. Used with the low-level `socket.on(event, callback)`.
 * Typed wrappers (`onOpen`, `onMessage`, etc.) are provided via [[SocketOps]] in the package
 * object.
 */
sealed trait SocketEvent {
  def name: String
}

object SocketEvent {
  case object Open extends SocketEvent { val name = "open" }
  case object Message extends SocketEvent { val name = "message" }
  case object BinaryMessage extends SocketEvent { val name = "binaryMessage" }
  case object Ping extends SocketEvent { val name = "ping" }
  case object Pong extends SocketEvent { val name = "pong" }
  case object Close extends SocketEvent { val name = "close" }
  case object Error extends SocketEvent { val name = "error" }
}
