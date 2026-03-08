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

package org.virtuslab.scalajs.k6.execution

import org.virtuslab.scalajs.k6.options.Options

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("k6/execution", JSImport.Namespace)
object Execution extends js.Object {
  // Scenario information
  val scenario: Scenario = js.native

  // Instance information
  val instance: Instance = js.native

  // Test control
  val test: Test = js.native

  // Virtual user information
  val vu: VU = js.native
}

@js.native
trait Scenario extends js.Object {
  // The assigned name of the running scenario.
  val name: String = js.native
  // The name of the running Executor type.
  val executor: String = js.native
  // The Unix timestamp in milliseconds when the scenario started.
  val startTime: Double = js.native
  // Percentage in a 0 to 1 interval of the scenario progress.
  val progress: Double = js.native
  // The unique and zero-based sequential number of the current iteration in the scenario, across the current instance.
  val iterationInInstance: Int = js.native
  // The unique and zero-based sequential number of the current iteration in the scenario.
  val iterationInTest: Int = js.native
}

@js.native
trait Instance extends js.Object {
  // The number of prematurely interrupted iterations in the current instance.
  val iterationsInterrupted: Int = js.native
  // The number of completed iterations in the current instance.
  val iterationsCompleted: Int = js.native
  // The number of active VUs.
  val vusActive: Int = js.native
  // The number of currently initialized VUs.
  val vusInitialized: Int = js.native
  // The time passed from the start of the current test run in milliseconds.
  val currentTestRunDuration: Double = js.native
}

@js.native
trait Test extends js.Object {
  def abort(input: js.UndefOr[String] = js.undefined): Unit = js.native
  val options: Options = js.native
}

@js.native
trait VU extends js.Object {
  // The identifier of the iteration in the current instance.
  val iterationInInstance: Int = js.native
  // The identifier of the iteration in the current scenario.
  val iterationInScenario: Int = js.native
  // The identifier of the VU across the instance.
  val idInInstance: Int = js.native
  // The globally unique (across the whole test run) identifier of the VU.
  val idInTest: Int = js.native
  val tags: js.Dictionary[js.Any] = js.native
  val metrics: VUMetrics = js.native
}

@js.native
trait VUMetrics extends js.Object {
  // Map to set or get VU tags.
  val tags: js.Dictionary[js.Any] = js.native
  // Map to set or get VU metadata.
  val metadata: js.Dictionary[js.Any] = js.native
}
