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
import scala.scalajs.js.annotation.JSGlobal
import scala.scalajs.js.typedarray.ArrayBufferView

/**
 * Native facade for the global `crypto` object exposed by k6.
 *
 * This mirrors the Web Crypto API surface used by k6 and is kept package-private.
 * Public, Scala-idiomatic entry points are defined in the `webcrypto` package object.
 */
@js.native
@JSGlobal("crypto")
private[webcrypto] object WebCrypto extends js.Object {

  def getRandomValues[T <: ArrayBufferView](array: T): T = js.native

  def randomUUID(): String = js.native

  val subtle: SubtleCrypto = js.native
}
