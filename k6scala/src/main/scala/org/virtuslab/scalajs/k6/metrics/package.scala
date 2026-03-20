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

import scala.scalajs.js
import scala.scalajs.js.JSConverters._

/** Custom metrics: Counter, Gauge, Rate, Trend ([`k6/metrics`](https://grafana.com/docs/k6/latest/javascript-api/k6-metrics/)). */
package object metrics {

  /**
   * Shared helper for converting optional Scala tag maps into the JS representation expected by
   * k6 metric constructors and `add` methods.
   *
   * All custom metric wrappers in this package should use this helper to keep the Scala → JS
   * boundary consistent.
   */
  private[metrics] def tagsToJS(
      tags: Option[Map[String, String]]
  ): js.UndefOr[js.Dictionary[String]] =
    tags.map(_.toJSDictionary).orUndefined
}
