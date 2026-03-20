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

package org.virtuslab.scalajs.k6.jslib.utils

import scala.scalajs.js
import js.JSConverters._

import scala.scalajs.js.annotation._

object RandomUtils {

  /** Random integer from `[min, max]` (inclusive). */
  def randomIntBetween(min: Int, max: Int): Int =
    RandomUtilsJslib.randomIntBetween(min, max)

  /** Random element from `items`. */
  def randomItem[T](items: Seq[T]): T = {
    val res = RandomUtilsJslib.randomItem(items.toJSArray)
    res.getOrElse(throw new NoSuchElementException("randomItem called with empty sequence"))
  }

  /** Random string of `length` using `charset` (if provided). */
  def randomString(length: Int, charset: Option[String] = None): String =
    RandomUtilsJslib.randomString(length, charset.orUndefined)

  @js.native
  @JSImport(
    "https://jslib.k6.io/k6-utils/1.2.0/index.js",
    JSImport.Namespace
  )
  private[utils] object RandomUtilsJslib extends js.Object {
    def randomIntBetween(min: Int, max: Int): Int = js.native
    def randomItem[T](items: js.Array[T]): js.UndefOr[T] = js.native
    def randomString(length: Int, charset: js.UndefOr[String]): String = js.native
  }
}
