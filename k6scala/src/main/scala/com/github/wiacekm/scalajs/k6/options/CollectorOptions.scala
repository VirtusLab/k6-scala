package com.github.wiacekm.scalajs.k6.options

import scala.scalajs.js
import scala.scalajs.js.annotation._

@js.native
trait CollectorOptions extends js.Object {
  @JSBracketAccess
  def apply(name: String): js.Any = js.native

  @JSBracketAccess
  def update(name: String, value: js.Any): Unit = js.native
}
