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

package org.virtuslab.scalajs.k6.http

import scala.scalajs.js

@js.native
trait ProtocolType extends js.Any

sealed trait Protocol {

  def asType: ProtocolType
}

object Protocol {
  case object `HTTP/1.0` extends Protocol {
    override def asType: ProtocolType = "HTTP/1.0".asInstanceOf[ProtocolType]
  }
  case object `HTTP/1.1` extends Protocol {
    override def asType: ProtocolType = "HTTP/1.1".asInstanceOf[ProtocolType]
  }
  case object `HTTP/2.0` extends Protocol {
    override def asType: ProtocolType = "HTTP/2.0".asInstanceOf[ProtocolType]
  }
}
