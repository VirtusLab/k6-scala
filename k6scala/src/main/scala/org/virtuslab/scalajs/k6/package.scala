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

package org.virtuslab.scalajs

import scala.scalajs.js
import js.JSConverters._
import scala.concurrent.duration.FiniteDuration

/** Scala-friendly entry points for the k6 core module (`import k6` in JS). */
package object k6 {

  /** Runs k6 checks against a value (typically an HTTP [[org.virtuslab.scalajs.k6.http.Response]]). */
  def check[T](
      response: T,
      sets: js.Dictionary[Check[T]],
      tags: Option[js.Dictionary[String]] = None
  ): Boolean = K6.check(response, sets, tags.orUndefined)

  /** Fails the iteration with an optional error string. */
  def fail(err: Option[String] = None): Unit = K6.fail(err.orUndefined)

  /** Groups metrics and logs under a named block. */
  def group(name: String, fn: js.Function): js.Object = K6.group(name, fn)

  /** Seeds the pseudo-random number generator for this VU. */
  def randomSeed(seed: Int): Unit = K6.randomSeed(seed)

  /** Pauses execution for the given duration (second granularity in the underlying k6 API). */
  def sleep(time: FiniteDuration): Unit = K6.sleep(time.toSeconds.toInt)
}
