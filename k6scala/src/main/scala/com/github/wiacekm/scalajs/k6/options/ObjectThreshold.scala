package com.github.wiacekm.scalajs.k6.options

import scala.scalajs.js
import scala.concurrent.duration._
import scala.scalajs.js.JSConverters._
import com.github.wiacekm.scalajs.k6.utils.DurationConverters._

@js.native
trait ObjectThreshold extends js.Object {
  val abortOnFail: js.UndefOr[Boolean] = js.native
  val delayAbortEval: js.UndefOr[FiniteDuration] = js.native
  val threshold: String = js.native
}

object ObjectThreshold {
  def apply(
      threshold: Int,
      abortOnFail: Option[Boolean] = None,
      delayAbortEval: Option[FiniteDuration] = None
  ): Stage =
    js.Dynamic
      .literal(
        threshold = threshold,
        abortOnFail = abortOnFail.orUndefined,
        delayAbortEval = delayAbortEval.map(_.toK6).orUndefined
      )
      .asInstanceOf[Stage]
}
