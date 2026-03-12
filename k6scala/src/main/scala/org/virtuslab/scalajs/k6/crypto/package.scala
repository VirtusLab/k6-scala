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

import scala.scalajs.js.typedarray.ArrayBuffer

import org.virtuslab.scalajs.k6.crypto.HashAlgorithm
import org.virtuslab.scalajs.k6.crypto.OutputEncoding
import org.virtuslab.scalajs.k6.crypto.Crypto
import org.virtuslab.scalajs.k6.crypto.Hasher

package object crypto {

  private def toJs(algorithm: HashAlgorithm): String =
    HashAlgorithm.toJs(algorithm)

  private def toJs(encoding: OutputEncoding): String =
    OutputEncoding.toJs(encoding)

  def md4(input: String, outputEncoding: OutputEncoding = OutputEncoding.Hex): String =
    Crypto.md4(input, toJs(outputEncoding))

  def md5(input: String, outputEncoding: OutputEncoding = OutputEncoding.Hex): String =
    Crypto.md5(input, toJs(outputEncoding))

  def sha1(input: String, outputEncoding: OutputEncoding = OutputEncoding.Hex): String =
    Crypto.sha1(input, toJs(outputEncoding))

  def sha256(input: String, outputEncoding: OutputEncoding = OutputEncoding.Hex): String =
    Crypto.sha256(input, toJs(outputEncoding))

  def sha384(input: String, outputEncoding: OutputEncoding = OutputEncoding.Hex): String =
    Crypto.sha384(input, toJs(outputEncoding))

  def sha512(input: String, outputEncoding: OutputEncoding = OutputEncoding.Hex): String =
    Crypto.sha512(input, toJs(outputEncoding))

  def sha512_224(input: String, outputEncoding: OutputEncoding = OutputEncoding.Hex): String =
    Crypto.sha512_224(input, toJs(outputEncoding))

  def sha512_256(input: String, outputEncoding: OutputEncoding = OutputEncoding.Hex): String =
    Crypto.sha512_256(input, toJs(outputEncoding))

  def ripemd160(input: String, outputEncoding: OutputEncoding = OutputEncoding.Hex): String =
    Crypto.ripemd160(input, toJs(outputEncoding))

  def hmac(
      algorithm: HashAlgorithm,
      secret: String,
      data: String,
      outputEncoding: OutputEncoding = OutputEncoding.Hex
  ): String =
    Crypto.hmac(toJs(algorithm), secret, data, toJs(outputEncoding))

  def createHash(algorithm: HashAlgorithm): Hasher =
    Crypto.createHash(toJs(algorithm))

  def createHMAC(algorithm: HashAlgorithm, secret: String): Hasher =
    Crypto.createHMAC(toJs(algorithm), secret)

  def randomBytes(count: Int): ArrayBuffer =
    Crypto.randomBytes(count)
}

