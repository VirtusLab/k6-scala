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

package org.virtuslab.scalajs.k6.jslib.chaijs

import scala.scalajs.js

/**
 * Scala wrappers around the k6-chaijs `expect(...)` assertion object.
 *
 * The underlying API is a fluent JavaScript chain (e.g. `expect(x).to.equal(y)`).
 * This wrapper intentionally exposes a small, Scala-friendly subset.
 */
final class Expectation private[chaijs] (private val native: js.Dynamic) {

  private def matchers: Matchers =
    new Matchers(native)

  /**
   * Asserts strict equality (`===`) using k6-chaijs / Chai semantics.
   *
   * Throws if the assertion fails (as per k6-chaijs / Chai behavior).
   */
  def toEqual(expected: js.Any): Unit = {
    matchers.equal(expected)
  }

  /** Asserts strict equality against a Scala `Int`. */
  def toEqual(expected: Int): Unit =
    toEqual(expected.asInstanceOf[js.Any])

  /** Asserts strict equality against a Scala `Double`. */
  def toEqual(expected: Double): Unit =
    toEqual(expected.asInstanceOf[js.Any])

  /** Asserts strict equality against a Scala `Boolean`. */
  def toEqual(expected: Boolean): Unit =
    toEqual(expected.asInstanceOf[js.Any])

  /** Asserts strict equality against a Scala `String`. */
  def toEqual(expected: String): Unit =
    toEqual(expected.asInstanceOf[js.Any])

  /**
   * Asserts that the numeric value is within `[min, max]`.
   *
   * Maps to k6-chaijs / Chai: `expect(value).to.be.within(min, max)`.
   */
  def toBeWithin(min: Double, max: Double): Unit = {
    matchers.within(min, max)
  }

  /** Asserts that the numeric value is within `[min, max]`. */
  def toBeWithin(min: Int, max: Int): Unit =
    toBeWithin(min.toDouble, max.toDouble)
}
