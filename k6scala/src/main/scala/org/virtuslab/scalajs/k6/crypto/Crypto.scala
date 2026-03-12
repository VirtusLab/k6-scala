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

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.typedarray.ArrayBuffer

/**
 * Native facade for the `k6/crypto` module.
 *
 * This object mirrors the JavaScript API surface and is kept package-private. Public,
 * Scala-idiomatic entry points are defined in the `org.virtuslab.scalajs.k6.crypto` package
 * object.
 */
@js.native
@JSImport("k6/crypto", JSImport.Namespace)
private[crypto] object Crypto extends js.Object {

  def md4(input: String, outputEncoding: String): String  = js.native
  def md5(input: String, outputEncoding: String): String  = js.native
  def sha1(input: String, outputEncoding: String): String = js.native

  def sha256(input: String, outputEncoding: String): String = js.native
  def sha384(input: String, outputEncoding: String): String = js.native

  def sha512(input: String, outputEncoding: String): String     = js.native
  def sha512_224(input: String, outputEncoding: String): String = js.native
  def sha512_256(input: String, outputEncoding: String): String = js.native
  def ripemd160(input: String, outputEncoding: String): String  = js.native

  def hmac(
      algorithm: String,
      secret: String,
      data: String,
      outputEncoding: String
  ): String = js.native

  def createHash(algorithm: String): Hasher                = js.native
  def createHMAC(algorithm: String, secret: String): Hasher = js.native

  /** Generate cryptographically strong random bytes as an ArrayBuffer. */
  def randomBytes(count: Int): ArrayBuffer = js.native
}

