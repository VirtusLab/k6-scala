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

/** Native k6 Counter class. Cumulative metric that tracks the total number of added values. */
@js.native
@JSImport("k6/metrics", "Counter")
private[metrics] class CounterNative(name: String) extends js.Object {

  /**
   * Add a value to the counter. Values can only increase semantically; adding negative values
   * is possible in k6 but discouraged. The metric name must be unique across the test run;
   * creating two Counters with the same name reuses the same metric.
   * @param value Value to add (number).
   * @param tags Optional tags for sub-metric filtering in thresholds (e.g. count by tag).
   */
  def add(value: Double, tags: js.UndefOr[js.Dictionary[String]] = js.undefined): Unit =
    js.native
}

/**
 * Scala wrapper for the k6 Counter custom metric. Use in thresholds with the same name and
 * expressions like `count < 100` or `count >= 200`.
 *
 * Edge cases (k6 runtime behaviour):
 *   - Empty name: k6 may accept it; behaviour is runtime-specific.
 *   - Adding 0: succeeds without error.
 *   - Very large values (e.g. Double.MaxValue): k6 may have implementation limits; document
 *     or test for your k6 version if needed.
 */
final class Counter private (private val inner: CounterNative) {

  /** Add a numeric value to the counter. */
  def add(value: Double, tags: Option[Map[String, String]] = None): Unit =
    inner.add(value, tagsToJS(tags))

  /** Add an integer value to the counter (convenience overload, untagged). */
  def add(value: Int): Unit =
    add(value.toDouble, None)

  /** Add an integer value to the counter with tags (convenience overload). */
  def add(value: Int, tags: Option[Map[String, String]]): Unit =
    add(value.toDouble, tags)

  /** Add 1 if true, 0 if false (convenience for success/failure counting, untagged). */
  def add(value: Boolean): Unit =
    add(if (value) 1.0 else 0.0, None)

  /** Add 1 if true, 0 if false with tags (convenience for success/failure counting). */
  def add(value: Boolean, tags: Option[Map[String, String]]): Unit =
    add(if (value) 1.0 else 0.0, tags)
}

object Counter {

  /** Create a new counter metric. The name must be unique; it is used in threshold expressions. */
  def apply(name: String): Counter = new Counter(new CounterNative(name))
}
