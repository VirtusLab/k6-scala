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
import scala.scalajs.js.annotation._

/**
 * k6-chaijs BDD-style testing DSL facades.
 *
 * The jslib module at
 * `https://jslib.k6.io/k6chaijs/4.5.0.1/index.js` exports `describe`, `expect`, and a
 * default Chai instance — it does '''not''' export Mocha-style `it()`. Structure
 * tests with nested [[describe]] blocks instead.
 *
 * Assertions throw when they fail (matching k6-chaijs / Chai behavior).
 *
 * Configuration (`chai.config` in JS) is available via [[config]].
 */
object ChaiJS {

  /**
   * Chai / k6-chaijs configuration (same object as `import chai from '...'; chai.config`).
   *
   * @see
   *   https://grafana.com/docs/k6/latest/javascript-api/jslib/k6chaijs/config/
   */
  def config: ChaiJsConfig =
    ChaiJslib.defaultExport.asInstanceOf[ChaiJsModuleDefault].config

  /**
   * Groups tests under a descriptive label.
   *
   * Maps to k6-chaijs: `describe(name, () => { ... })`.
   */
  def describe(name: String)(fn: => Unit): Unit = {
    val jsFn: js.Function0[Unit] = () => fn
    ChaiJslib.describe(name, jsFn)
  }

  /**
   * Starts an assertion against `value`.
   *
   * The returned [[Expectation]] can be used with `toEqual(...)` and
   * `toBeWithin(min, max)`.
   */
  def expect[A](value: A): Expectation =
    new Expectation(ChaiJslib.expect(value.asInstanceOf[js.Any]))
}

@js.native
@JSImport("https://jslib.k6.io/k6chaijs/4.5.0.1/index.js", JSImport.Namespace)
private[chaijs] object ChaiJslib extends js.Object {
  def describe(name: String, fn: js.Function0[Unit]): Unit = js.native
  def expect(value: js.Any): js.Dynamic = js.native

  /** Default export: the Chai instance (`import chai from '...'`). */
  @JSName("default")
  def defaultExport: js.Object = js.native
}
