package com.github.wiacekm.scalajs.k6.http

import scala.scalajs.js

@js.native
trait RequestCookie extends js.Object {
  def name: String = js.native
  def value: String = js.native
  def replace: Boolean = js.native
}
