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
import scala.scalajs.js.typedarray.ArrayBufferView
import scala.scalajs.js.typedarray.Uint8Array

@js.native
trait AesCbcParams extends js.Object {
  val name: String
  val iv: ArrayBufferView
}

object AesCbcParams {

  def apply(iv: ArrayBufferView): AesCbcParams =
    js.Dynamic
      .literal(
        name = "AES-CBC",
        iv = iv
      )
      .asInstanceOf[AesCbcParams]
}

@js.native
trait AesGcmParams extends js.Object {
  val name: String
  val iv: ArrayBufferView
  val additionalData: js.UndefOr[ArrayBufferView]
  val tagLength: js.UndefOr[Int]
}

object AesGcmParams {

  def apply(
      iv: ArrayBufferView,
      additionalData: js.UndefOr[ArrayBufferView] = js.undefined,
      tagLength: js.UndefOr[Int] = js.undefined
  ): AesGcmParams =
    js.Dynamic
      .literal(
        name = "AES-GCM",
        iv = iv,
        additionalData = additionalData,
        tagLength = tagLength
      )
      .asInstanceOf[AesGcmParams]
}

@js.native
trait AesKeyGenParams extends js.Object {
  val name: String
  val length: Int
}

object AesKeyGenParams {

  def aesGcm(length: Int): AesKeyGenParams =
    js.Dynamic
      .literal(
        name = "AES-GCM",
        length = length
      )
      .asInstanceOf[AesKeyGenParams]

  def aesCbc(length: Int): AesKeyGenParams =
    js.Dynamic
      .literal(
        name = "AES-CBC",
        length = length
      )
      .asInstanceOf[AesKeyGenParams]
}

@js.native
trait RsaHashedKeyGenParams extends js.Object {
  val name: String
  val modulusLength: Int
  val publicExponent: Uint8Array
  val hash: js.Object
}

object RsaHashedKeyGenParams {

  def apply(
      name: String,
      modulusLength: Int,
      publicExponent: Uint8Array,
      hashName: String
  ): RsaHashedKeyGenParams =
    js.Dynamic
      .literal(
        name = name,
        modulusLength = modulusLength,
        publicExponent = publicExponent,
        hash = js.Dynamic.literal(name = hashName)
      )
      .asInstanceOf[RsaHashedKeyGenParams]
}

