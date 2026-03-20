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
 * Chai configuration exposed by the k6-chaijs default export (`chai.config` in JS).
 *
 * See the k6 docs:
 * [[https://grafana.com/docs/k6/latest/javascript-api/jslib/k6chaijs/config/]]
 *
 * The k6-chaijs bundle may expose additional fields at runtime; this facade covers
 * the documented options.
 */
@js.native
trait ChaiJsConfig extends js.Object {

  /** Max length for interpolated values in assertion messages (default in k6-chaijs: 100). */
  var truncateVariableThreshold: Int = js.native

  /** Max length for the full `check()` message string (default in k6-chaijs: 300). */
  var truncateMsgThreshold: Int = js.native

  /**
   * When `true`, variable values are not interpolated into check messages (better for
   * high-iteration loads). Set to `false` for debugging or single-iteration scripts.
   */
  var aggregateChecks: Boolean = js.native

  /** When `true`, failed assertions may log extra debug output (WARN). */
  var logFailures: Boolean = js.native

  /**
   * When `true`, the test run may abort on the first failed Chai assertion (k6-chaijs
   * extension; not listed in all doc tables).
   */
  var exitOnError: Boolean = js.native
}

@js.native
private[chaijs] trait ChaiJsModuleDefault extends js.Object {
  val config: ChaiJsConfig = js.native
}
