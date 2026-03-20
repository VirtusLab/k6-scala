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

package org.virtuslab.scalajs.k6.jslib

/**
 * Utilities from [`k6-utils` 1.2.0](https://jslib.k6.io/k6-utils/1.2.0/) on jslib.
 *
 * Functions delegate to [[RandomUtils]] and [[StringUtils]] so you can import this package and call
 * `randomIntBetween` / `findBetween` at top level, like `http.get` wraps `Http`.
 */
package object utils {

  def randomIntBetween(min: Int, max: Int): Int =
    RandomUtils.randomIntBetween(min, max)

  def randomItem[T](items: Seq[T]): T =
    RandomUtils.randomItem(items)

  def randomString(length: Int, charset: Option[String] = None): String =
    RandomUtils.randomString(length, charset)

  def findBetween(content: String, left: String, right: String): Option[String] =
    StringUtils.findBetween(content, left, right)

  def normalizeToKebabCase(str: String): String =
    StringUtils.normalizeToKebabCase(str)

  def isURL(str: String): Boolean =
    StringUtils.isURL(str)

  def uuidv4(): String =
    StringUtils.uuidv4()
}
