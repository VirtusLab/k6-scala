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

package org.virtuslab.scalajs.k6.options

import scala.scalajs.js

sealed trait ExecutorOptionsType extends js.Any

object ExecutorOptionsType {
  val SharedIterations: ExecutorOptionsType = "shared-iterations".asInstanceOf[ExecutorOptionsType]
  val PerVuIterations: ExecutorOptionsType = "per-vu-iterations".asInstanceOf[ExecutorOptionsType]
  val ConstantVus: ExecutorOptionsType = "constant-vus".asInstanceOf[ExecutorOptionsType]
  val RampingVus: ExecutorOptionsType = "ramping-vus".asInstanceOf[ExecutorOptionsType]
  val ConstantArrivalRate: ExecutorOptionsType =
    "constant-arrival-rate".asInstanceOf[ExecutorOptionsType]
  val RampingArrivalRate: ExecutorOptionsType =
    "ramping-arrival-rate".asInstanceOf[ExecutorOptionsType]
  val ExternallyControlled: ExecutorOptionsType =
    "externally-controlled".asInstanceOf[ExecutorOptionsType]
}
