package com.github.wiacekm.scalajs.k6

import scala.scalajs.js
import js.JSConverters._

object Checkers {

  def apply[T](checkers: (String, Check[T])*): js.Dictionary[Check[T]] =
    checkers.toMap.toJSDictionary

}

@js.native
trait Check[T] extends js.Function1[T, Boolean]

object Check {

  def apply[T](check: T => Boolean): Check[T] = check.asInstanceOf[Check[T]]
}
