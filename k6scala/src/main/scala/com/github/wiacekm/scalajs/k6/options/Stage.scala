package com.github.wiacekm.scalajs.k6.options

import scala.scalajs.js
import com.github.wiacekm.scalajs.k6.utils.DurationConverters._
import scala.concurrent.duration.FiniteDuration

@js.native
trait Stage extends js.Object {
  val duration: String = js.native
  val target: Int = js.native
}

object Stage {
  def apply(duration: FiniteDuration, target: Int): Stage =
    js.Dynamic
      .literal(
        duration = duration.toK6,
        target = target
      )
      .asInstanceOf[Stage]
}
