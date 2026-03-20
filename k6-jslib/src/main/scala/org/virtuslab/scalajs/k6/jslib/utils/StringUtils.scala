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
import scala.scalajs.js.annotation._

object StringUtils {

  /**
   * Extracts the substring between the first occurrences of `left` and
   * the next `right` after it.
   *
   * Delegates to the `k6-utils` `findBetween` implementation.
   *
   * @return
   *   `None` if `left` or `right` is missing (or any delimiter is empty).
   */
  def findBetween(content: String, left: String, right: String): Option[String] = {
    val res = StringUtilsJslib.findBetween(content, left, right)
    if (res.isEmpty) None else Some(res.get)
  }

  /**
   * Normalizes a string into "kebab-case".
   *
   * Delegates to the `k6-utils` `normalizeToKebabCase` implementation.
   *
   * - Converts camelCase / PascalCase to kebab-case.
   * - Replaces spaces and underscores with hyphens.
   * - Replaces other non-alphanumeric characters with hyphens.
   * - Collapses multiple hyphens and trims leading/trailing hyphens.
   */
  def normalizeToKebabCase(str: String): String =
    StringUtilsJslib.normalizeToKebabCase(str)

  /**
   * Validates whether `str` looks like an absolute HTTP/HTTPS URL.
   *
   * Delegates to the `k6-utils` `isURL` implementation.
   */
  def isURL(str: String): Boolean =
    StringUtilsJslib.isURL(str)

  /**
   * Generates a random UUID version 4.
   *
   * Delegates to the `k6-utils` `uuidv4` implementation.
   */
  def uuidv4(): String = StringUtilsJslib.uuidv4()

  @js.native
  @JSImport(
    "https://jslib.k6.io/k6-utils/1.2.0/index.js",
    JSImport.Namespace
  )
  private[utils] object StringUtilsJslib extends js.Object {
    def findBetween(content: String, left: String, right: String): js.UndefOr[
      String
    ] = js.native
    def normalizeToKebabCase(str: String): String = js.native
    def isURL(str: String): Boolean = js.native
    def uuidv4(): String = js.native
  }
}
