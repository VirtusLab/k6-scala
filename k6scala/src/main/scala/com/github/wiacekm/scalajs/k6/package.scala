package com.github.wiacekm.scalajs.k6

import scala.scalajs.js
import js.JSConverters._
import scala.concurrent.duration.FiniteDuration

package object k6 {
  def check[T](
      response: T,
      sets: js.Dictionary[Check[T]],
      tags: Option[js.Dictionary[String]] = None
  ): Boolean = K6.check(response, sets, tags.orUndefined)
  def fail(err: Option[String] = None): Unit = K6.fail(err.orUndefined)
  def group(name: String, fn: js.Function): js.Object = K6.group(name, fn)
  def randomSeed(seed: Int): Unit = K6.randomSeed(seed)
  def sleep(time: FiniteDuration): Unit = K6.sleep(time.toSeconds.toInt)
}
