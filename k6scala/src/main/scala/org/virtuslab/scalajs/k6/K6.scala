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
import scala.scalajs.js.annotation._

@js.native
@JSImport("k6", JSImport.Namespace)
private[k6] object K6 extends js.Object {
  def check[T](
      response: T,
      sets: js.Dictionary[Check[T]],
      tags: js.UndefOr[js.Dictionary[String]] = js.undefined
  ): Boolean = js.native
  def fail(err: js.UndefOr[String] = js.undefined): Unit = js.native
  def group(name: String, fn: js.Function): js.Object = js.native
  def randomSeed(seed: Int): Unit = js.native
  def sleep(time: Int): Unit = js.native
}
