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

package org.virtuslab.scalajs.k6

/**
 * Runtime execution context from [`k6/execution`](https://grafana.com/docs/k6/latest/javascript-api/k6-execution/)
 * (scenario, VU, instance, test).
 *
 * Top-level values delegate to [[Execution]] so you can write `execution.scenario` after
 * `import org.virtuslab.scalajs.k6.execution._`, mirroring how `http.get` wraps the native module.
 */
package object execution {

  /** The running scenario’s metadata (see [[Execution.scenario]]). */
  def scenario: Scenario = Execution.scenario

  /** The current load-generator instance (see [[Execution.instance]]). */
  def instance: Instance = Execution.instance

  /** Test-wide control and options (see [[Execution.test]]). */
  def test: Test = Execution.test

  /** The current virtual user (see [[Execution.vu]]). */
  def vu: VU = Execution.vu
}
