package com.github.wiacekm.scalajs.k6.options

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
