package com.github.wiacekm.scalajs.k6.http

import scala.scalajs.js
import scala.scalajs.js.annotation._

@js.native
trait CookieJarCookies extends js.Object {
  @JSBracketAccess
  def apply(name: String): js.Array[String] = js.native

  @JSBracketAccess
  def update(name: String, value: js.Array[String]): Unit = js.native
}
