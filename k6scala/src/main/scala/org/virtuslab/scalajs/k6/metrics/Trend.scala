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

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/** Native k6 Trend class. Tracks statistical distributions (min, max, avg, median, percentiles). */
@js.native
@JSImport("k6/metrics", "Trend")
private[metrics] class TrendNative(name: String, isTime: js.UndefOr[Boolean]) extends js.Object {

  /**
   * Add a value to the trend metric.
   *
   * In threshold expressions the variables are `avg`, `min`, `max`, `med`, and `p(N)` (e.g.
   * `p(95) < 500` for 95th percentile under 500ms). When `isTime` is true, values are treated as
   * milliseconds and output formatting reflects that.
   *
   * @param value
   *   numeric value to record (e.g. duration in milliseconds)
   * @param tags
   *   optional tags for sub-metric filtering in thresholds
   */
  def add(value: Double, tags: js.UndefOr[js.Dictionary[String]] = js.undefined): Unit =
    js.native
}

/**
 * Scala wrapper for the k6 Trend custom metric.
 *
 * Use for response times, processing durations, or any numeric measurement where you need
 * percentile analysis. In threshold expressions use `avg`, `min`, `max`, `med`, or `p(N)` (e.g.
 * `p(95) < 500`); all are in the same unit as added values (typically milliseconds when
 * `isTime = true`).
 *
 * Edge cases (k6 runtime behaviour):
 *   - Adding zero is valid (e.g. 0ms is a legitimate timing).
 *   - Negative values are accepted by k6 but are semantically meaningless for timings; document
 *     or avoid for your use case.
 */
final class Trend private (private val inner: TrendNative) {

  /** Add a numeric value to the trend (e.g. duration in milliseconds). */
  def add(value: Double, tags: Option[Map[String, String]] = None): Unit =
    inner.add(value, tagsToJS(tags))

  /**
   * Add a duration as milliseconds. Convenience overload for timing metrics; the duration is
   * converted to milliseconds before being passed to k6.
   */
  def add(duration: FiniteDuration): Unit =
    inner.add(duration.toMillis.toDouble, js.undefined)
}

object Trend {

  /**
   * Create a new trend metric.
   *
   * @param name
   *   metric name used in k6 output and threshold definitions
   * @param isTime
   *   when true, values are treated as time (milliseconds) and k6 formats output accordingly
   */
  def apply(name: String, isTime: Boolean = false): Trend =
    new Trend(new TrendNative(name, isTime))
}
