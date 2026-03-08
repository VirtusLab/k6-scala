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

package org.virtuslab.scalajs.k6.timers

import scala.scalajs.js
import scala.scalajs.js.annotation._

@js.native
trait TimerId extends js.Any

@js.native
trait IntervalId extends js.Any

@js.native
@JSImport("k6/timers", JSImport.Namespace)
object Timers extends js.Object {
  def setTimeout(callback: js.Function, delay: Long): TimerId = js.native
  def setInterval(callback: js.Function, interval: Long): IntervalId = js.native
  def clearTimeout(timerId: TimerId): Unit = js.native
  def clearInterval(intervalId: IntervalId): Unit = js.native
}
