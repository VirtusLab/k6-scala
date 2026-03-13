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

package org.virtuslab.scalajs.k6.crypto

/**
 * Output encodings supported by `k6/crypto` hash and HMAC functions.
 *
 * The underlying k6 API also supports additional encodings such as `"binary"` and `"base64url"`;
 * this facade focuses on the string encodings that are most commonly used in load tests.
 */
sealed trait OutputEncoding

object OutputEncoding {

  case object Hex extends OutputEncoding
  case object Base64 extends OutputEncoding
  case object Base64RawURL extends OutputEncoding

  private[crypto] def toJs(encoding: OutputEncoding): String =
    encoding match {
      case Hex => "hex"
      case Base64 => "base64"
      case Base64RawURL => "base64rawurl"
    }
}
