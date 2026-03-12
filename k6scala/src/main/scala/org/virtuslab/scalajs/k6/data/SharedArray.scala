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

package org.virtuslab.scalajs.k6.data

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.JSConverters._
import scala.collection.Seq

/** k6 SharedArray facade. Represents an array-like, read-only data structure shared across VUs. */
@js.native
trait SharedArray[A] extends js.Array[A]

@js.native
@JSImport("k6/data", "SharedArray")
private[data] class SharedArrayCtor[A](
    name: String,
    init: js.Function0[js.Array[A]]
) extends SharedArray[A]

object SharedArray {

  /** Construct a k6 SharedArray using the native constructor and a JS initializer function.
    *
    * The initializer function is evaluated once during the k6 init stage. Its resulting
    * `js.Array[A]` is stored and shared across all VUs without additional memory copies.
    */
  def apply[A](name: String, init: js.Function0[js.Array[A]]): SharedArray[A] =
    new SharedArrayCtor[A](name, init)

  /** Scala-friendly constructor that builds the shared array from a Scala `Seq[A]`.
    *
    * The by-name initializer is evaluated once during the k6 init stage. Its elements are
    * converted to a `js.Array[A]` and shared across all VUs.
    *
    * '''Important''': k6 SharedArray serialises elements by copying own properties. Scala case
    * classes have mangled internal field names (`LMyClass__f_field`) and expose Scala-named
    * accessors only via prototype methods, which are lost during k6's copy. To preserve field
    * names, use types that extend `js.Object` (Scala.js-defined JS classes), `js.Dynamic.literal`,
    * or `js.Dictionary` — these store fields as plain JS own properties with their Scala names.
    */
  def fromSeq[A](name: String)(init: => Seq[A]): SharedArray[A] =
    apply(
      name,
      () => init.toJSArray
    )
}
