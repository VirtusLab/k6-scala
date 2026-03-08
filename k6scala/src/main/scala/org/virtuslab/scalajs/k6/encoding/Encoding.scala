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

package org.virtuslab.scalajs.k6.encoding

import scala.scalajs.js
import scala.scalajs.js.|
import scala.scalajs.js.annotation._
import scala.scalajs.js.typedarray.ArrayBuffer

@js.native
@JSImport("k6/encoding", JSImport.Namespace)
object Encoding extends js.Object {

  /**
   * Base64 decode a string.
   * @param input The string to base64 decode.
   * @param encoding Base64 variant (optional).
   * @return The base64 decoded version of the input string as ArrayBuffer.
   */
  def b64decode(input: String, encoding: js.UndefOr[Base64Variant] = js.undefined): ArrayBuffer =
    js.native

  /**
   * Base64 decode a string to string format.
   * @param input The string to base64 decode.
   * @param encoding Base64 variant.
   * @param format Format of the decoded string.
   * @return The base64 decoded version of the input string as String.
   */
  def b64decode(input: String, encoding: Base64Variant, format: Format): String = js.native

  /**
   * Base64 encode a string or ArrayBuffer.
   * @param input String to encode or ArrayBuffer object.
   * @param encoding Base64 variant (optional).
   * @return Base64 encoded string.
   */
  def b64encode(
      input: String | ArrayBuffer,
      encoding: js.UndefOr[Base64Variant] = js.undefined
  ): String = js.native
}

/**
 * Base64 variant type.
 */
object Base64Variant {
  val Std: Base64Variant = "std".asInstanceOf[Base64Variant]
  val RawStd: Base64Variant = "rawstd".asInstanceOf[Base64Variant]
  val Url: Base64Variant = "url".asInstanceOf[Base64Variant]
  val RawUrl: Base64Variant = "rawurl".asInstanceOf[Base64Variant]
}

sealed trait Base64Variant extends js.Any
