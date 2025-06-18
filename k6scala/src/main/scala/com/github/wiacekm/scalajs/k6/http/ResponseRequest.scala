package com.github.wiacekm.scalajs.k6.http

import scala.scalajs.js

@js.native
trait ResponseRequest extends js.Object {
  def body: String = js.native
  def cookies: js.Dictionary[js.Array[RequestCookie]] = js.native
  def headers: js.Dictionary[js.Array[String]] = js.native
  def method: String = js.native
  def url: String = js.native
}
