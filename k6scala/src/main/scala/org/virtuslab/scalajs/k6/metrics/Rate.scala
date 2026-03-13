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

/** Native k6 Rate class. Tracks the percentage of non-zero values added. */
@js.native
@JSImport("k6/metrics", "Rate")
private[metrics] class RateNative(name: String) extends js.Object {

  /**
   * Record a value for the rate metric.
   *
   * A Rate tracks the fraction of non-zero values over all added samples. The value parameter is
   * typically 0/1 or a boolean converted to 0/1. In threshold expressions, the variable name is
   * `rate`, for example: `rate < 0.1` (less than 10% non-zero values).
   *
   * @param value
   *   numeric value to record; any non-zero value counts as a "hit"
   * @param tags
   *   optional tags for sub-metric filtering in thresholds
   */
  def add(value: Double, tags: js.UndefOr[js.Dictionary[String]] = js.undefined): Unit =
    js.native
}

/**
 * Scala wrapper for the k6 Rate custom metric.
 *
 * Usage examples:
 * {{{
 *   val errorRate = Rate("error_rate")
 *   errorRate.add(response.status >= 400) // Boolean overload
 *   errorRate.add(1.0, Some(Map("endpoint" -> "/api"))) // numeric overload with tags
 * }}}
 *
 * Behaviour notes (k6 runtime):
 *   - Any non-zero value is treated as a "hit".
 *   - The effective rate ranges from 0.0 to 1.0.
 *   - In threshold expressions, use `rate` (e.g. `rate < 0.1`).
 */
final class Rate private (private val inner: RateNative) {

  /** Record a numeric value for the rate metric. */
  def add(value: Double, tags: Option[Map[String, String]] = None): Unit =
    inner.add(value, tagsToJS(tags))

  /** Convenience overload for integer values (untagged). */
  def add(value: Int): Unit =
    add(value.toDouble, None)

  /** Convenience overload for integer values with tags. */
  def add(value: Int, tags: Option[Map[String, String]]): Unit =
    add(value.toDouble, tags)

  /**
   * Convenience overload mapping `true` to 1.0 and `false` to 0.0.
   *
   * This is the idiomatic way to use Rate metrics in k6, for example:
   * {{{
   *   errorRate.add(response.status == 200)
   * }}}
   */
  def add(value: Boolean): Unit =
    add(if (value) 1.0 else 0.0, None)

  /** Convenience overload mapping `true` to 1.0 and `false` to 0.0, with tags. */
  def add(value: Boolean, tags: Option[Map[String, String]]): Unit =
    add(if (value) 1.0 else 0.0, tags)
}

object Rate {

  /** Create a new rate metric. The name is used in k6 output and threshold definitions. */
  def apply(name: String): Rate = new Rate(new RateNative(name))
}
