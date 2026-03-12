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

package org.virtuslab.scalajs.k6.metrics

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** Native k6 Gauge class. Tracks the most recent value set, plus min/max/avg over the test run. */
@js.native
@JSImport("k6/metrics", "Gauge")
private[metrics] class GaugeNative(name: String) extends js.Object {

  /**
   * Set the current gauge value.
   *
   * Gauges represent measurements that can go up or down between calls, such as queue length
   * or active connection counts. In threshold expressions the variable name is `value`,
   * for example: `value < 100`.
   *
   * @param value
   *   numeric value to record (can increase or decrease)
   * @param tags
   *   optional tags for sub-metric filtering in thresholds
   */
  def add(value: Double, tags: js.UndefOr[js.Dictionary[String]] = js.undefined): Unit =
    js.native
}

/**
 * Scala wrapper for the k6 Gauge custom metric.
 *
 * Behaviour notes (k6 runtime):
 *   - Values may increase or decrease between calls; only the latest value is kept for `value`.
 *   - Negative values are allowed and treated as regular numbers.
 *   - In threshold expressions, use `value` (e.g. `value < 100`).
 */
final class Gauge private (private val inner: GaugeNative) {

  /** Record a numeric value for the gauge. */
  def add(value: Double, tags: Option[Map[String, String]] = None): Unit =
    inner.add(value, tagsToJS(tags))

  /** Convenience overload for integer values (untagged). */
  def add(value: Int): Unit =
    add(value.toDouble, None)

  /** Convenience overload for integer values with tags. */
  def add(value: Int, tags: Option[Map[String, String]]): Unit =
    add(value.toDouble, tags)

  /**
   * Convenience overload that maps `true` to 1.0 and `false` to 0.0.
   *
   * While this pattern is primarily used with Rate metrics in k6, it can be useful for gauges
   * that occasionally act as on/off indicators.
   */
  def add(value: Boolean): Unit =
    add(if (value) 1.0 else 0.0, None)

  /** Convenience overload that maps `true` to 1.0 and `false` to 0.0, with tags. */
  def add(value: Boolean, tags: Option[Map[String, String]]): Unit =
    add(if (value) 1.0 else 0.0, tags)
}

object Gauge {

  /** Create a new gauge metric. The name is used in k6 output and threshold definitions. */
  def apply(name: String): Gauge = new Gauge(new GaugeNative(name))
}
