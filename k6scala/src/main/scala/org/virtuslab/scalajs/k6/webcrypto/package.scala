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

package org.virtuslab.scalajs.k6

import scala.scalajs.js
import scala.scalajs.js.typedarray.ArrayBuffer
import scala.scalajs.js.typedarray.ArrayBufferView

import org.virtuslab.scalajs.k6.webcrypto._

package object webcrypto {

  def getRandomValues[T <: ArrayBufferView](array: T): T =
    WebCrypto.getRandomValues(array)

  def randomUUID(): String =
    WebCrypto.randomUUID()

  def subtle: SubtleCrypto =
    WebCrypto.subtle

  def encrypt(
      algorithm: js.Object,
      key: CryptoKey,
      data: js.Any
  ): js.Promise[ArrayBuffer] =
    subtle.encrypt(algorithm, key, data)

  def decrypt(
      algorithm: js.Object,
      key: CryptoKey,
      data: js.Any
  ): js.Promise[ArrayBuffer] =
    subtle.decrypt(algorithm, key, data)

  def digest(
      algorithm: String,
      data: js.Any
  ): js.Promise[ArrayBuffer] =
    subtle.digest(algorithm, data)

  def generateSecretKey(
      algorithm: AesKeyGenParams,
      extractable: Boolean,
      usages: Seq[KeyUsage]
  ): js.Promise[CryptoKey] =
    subtle
      .generateKey(
        algorithm,
        extractable,
        KeyUsage.toJsArray(usages)
      )
      .asInstanceOf[js.Promise[CryptoKey]]

  def generateKeyPair(
      algorithm: RsaHashedKeyGenParams,
      extractable: Boolean,
      usages: Seq[KeyUsage]
  ): js.Promise[CryptoKeyPair] =
    subtle
      .generateKey(
        algorithm,
        extractable,
        KeyUsage.toJsArray(usages)
      )
      .asInstanceOf[js.Promise[CryptoKeyPair]]

  def importKey(
      format: KeyFormat,
      keyData: js.Any,
      algorithm: js.Object,
      extractable: Boolean,
      usages: Seq[KeyUsage]
  ): js.Promise[CryptoKey] =
    subtle.importKey(
      KeyFormat.toJs(format),
      keyData,
      algorithm,
      extractable,
      KeyUsage.toJsArray(usages)
    )

  def exportKey(
      format: KeyFormat,
      key: CryptoKey
  ): js.Promise[js.Any] =
    subtle.exportKey(KeyFormat.toJs(format), key)
}
