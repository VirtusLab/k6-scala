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
import scala.scalajs.js.typedarray.ArrayBuffer

/**
 * Native facade for the Web Crypto API `SubtleCrypto` interface.
 *
 * All methods return JavaScript promises. Public Scala wrappers are defined
 * in the `webcrypto` package object.
 *
 * The JavaScript Web Crypto API uses a number of union types such as
 * `CryptoKey | CryptoKeyPair` and `BufferSource`. To keep the shared sources
 * Scala 2.12-compatible, these are represented with `js.Any` where necessary
 * and narrowed in Scala helpers when a more precise type is expected.
 */
@js.native
trait SubtleCrypto extends js.Object {

  def encrypt(
      algorithm: js.Object,
      key: CryptoKey,
      data: js.Any
  ): js.Promise[ArrayBuffer] = js.native

  def decrypt(
      algorithm: js.Object,
      key: CryptoKey,
      data: js.Any
  ): js.Promise[ArrayBuffer] = js.native

  def sign(
      algorithm: js.Object,
      key: CryptoKey,
      data: js.Any
  ): js.Promise[ArrayBuffer] = js.native

  def verify(
      algorithm: js.Object,
      key: CryptoKey,
      signature: js.Any,
      data: js.Any
  ): js.Promise[Boolean] = js.native

  def digest(
      algorithm: String,
      data: js.Any
  ): js.Promise[ArrayBuffer] = js.native

  def generateKey(
      algorithm: js.Object,
      extractable: Boolean,
      keyUsages: js.Array[String]
  ): js.Promise[js.Any] = js.native

  def importKey(
      format: String,
      keyData: js.Any,
      algorithm: js.Object,
      extractable: Boolean,
      keyUsages: js.Array[String]
  ): js.Promise[CryptoKey] = js.native

  def exportKey(
      format: String,
      key: CryptoKey
  ): js.Promise[js.Any] = js.native

  def deriveBits(
      algorithm: js.Object,
      baseKey: CryptoKey,
      length: Int
  ): js.Promise[ArrayBuffer] = js.native

  def deriveKey(
      algorithm: js.Object,
      baseKey: CryptoKey,
      derivedKeyType: js.Object,
      extractable: Boolean,
      keyUsages: js.Array[String]
  ): js.Promise[CryptoKey] = js.native
}
