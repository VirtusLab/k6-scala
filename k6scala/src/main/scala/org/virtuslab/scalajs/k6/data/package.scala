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

import scala.collection.Seq
import org.virtuslab.scalajs.k6.data.SharedArray

package object data {

  /** Scala-friendly helper for constructing a k6 SharedArray from a Scala `Seq[A]`.
    *
    * The initializer is evaluated once during the k6 init stage and its elements are shared
    * across all VUs via a k6 `SharedArray`.
    */
  def sharedArrayFromSeq[A](name: String)(init: => Seq[A]): SharedArray[A] =
    SharedArray.fromSeq(name)(init)
}

