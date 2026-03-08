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

import scala.concurrent.duration.FiniteDuration

package object timers {

  def setTimeout(callback: => Unit, delay: FiniteDuration): TimerId =
    Timers.setTimeout(() => callback, delay.toMillis)

  def setInterval(callback: => Unit, interval: FiniteDuration): IntervalId =
    Timers.setInterval(() => callback, interval.toMillis)

  def clearTimeout(timerId: TimerId): Unit =
    Timers.clearTimeout(timerId)

  def clearInterval(timerId: IntervalId): Unit =
    Timers.clearInterval(timerId)
}
