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

/**
 * Native facade for a Web Crypto API key object.
 *
 * See:
 * https://developer.mozilla.org/en-US/docs/Web/API/CryptoKey
 */
@js.native
trait CryptoKey extends js.Object {

  val `type`: String = js.native

  val extractable: Boolean = js.native

  val algorithm: js.Object = js.native

  val usages: js.Array[String] = js.native
}

/**
 * Native facade for a key pair returned by `SubtleCrypto.generateKey`.
 */
@js.native
trait CryptoKeyPair extends js.Object {

  val publicKey: CryptoKey = js.native

  val privateKey: CryptoKey = js.native
}
