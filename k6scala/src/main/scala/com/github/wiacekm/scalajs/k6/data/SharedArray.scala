package com.github.wiacekm.scalajs.k6.data

import scala.scalajs.js

@js.native
trait SharedArray[A] extends js.Array[A]

object SharedArray {

  def apply[A](name: String, data: js.Function0[A]): SharedArray[A] =
    js.Dynamic.literal(name = name, data = data).asInstanceOf[SharedArray[A]]
}
