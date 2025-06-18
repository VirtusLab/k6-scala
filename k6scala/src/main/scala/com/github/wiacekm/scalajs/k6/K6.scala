package com.github.wiacekm.scalajs.k6

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
