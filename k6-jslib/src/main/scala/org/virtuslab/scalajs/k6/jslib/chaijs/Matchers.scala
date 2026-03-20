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
 * Internal wrappers around the k6-chaijs / Chai assertion chain.
 *
 * This keeps `Expectation` focused on Scala-friendly method names.
 */
final class Matchers private[chaijs] (private val native: js.Dynamic) {

  /**
   * Chai assertion methods are unbound functions: calling `equal(expected)` without a
   * receiver leaves `this` undefined and breaks internal `__flags` access. We must
   * invoke with the assertion object as `this` (same as `expect(x).to.equal(y)` in JS).
   */
  def equal(expected: js.Any): Unit = {
    val equalFn = native.selectDynamic("to").selectDynamic("equal").asInstanceOf[
      js.ThisFunction1[js.Any, js.Any, js.Any]
    ]
    equalFn(native, expected)
    ()
  }

  def within(min: Double, max: Double): Unit = {
    val withinFn = native
      .selectDynamic("to")
      .selectDynamic("be")
      .selectDynamic("within")
      .asInstanceOf[js.ThisFunction2[js.Any, Double, Double, js.Any]]

    withinFn(native, min, max)
    ()
  }
}

