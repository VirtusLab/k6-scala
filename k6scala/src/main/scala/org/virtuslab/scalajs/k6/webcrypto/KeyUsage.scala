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

package org.virtuslab.scalajs.k6.webcrypto

import scala.scalajs.js
import scala.scalajs.js.JSConverters._

sealed trait KeyUsage

object KeyUsage {

  case object Encrypt extends KeyUsage
  case object Decrypt extends KeyUsage
  case object Sign extends KeyUsage
  case object Verify extends KeyUsage
  case object DeriveKey extends KeyUsage
  case object DeriveBits extends KeyUsage
  case object WrapKey extends KeyUsage
  case object UnwrapKey extends KeyUsage

  def toJs(usage: KeyUsage): String =
    usage match {
      case Encrypt => "encrypt"
      case Decrypt => "decrypt"
      case Sign => "sign"
      case Verify => "verify"
      case DeriveKey => "deriveKey"
      case DeriveBits => "deriveBits"
      case WrapKey => "wrapKey"
      case UnwrapKey => "unwrapKey"
    }

  def toJsArray(usages: Seq[KeyUsage]): js.Array[String] =
    usages.map(toJs).toJSArray
}
