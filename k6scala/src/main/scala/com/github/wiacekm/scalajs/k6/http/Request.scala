package com.github.wiacekm.scalajs.k6.http

import scala.scalajs.{js => js}

@js.native
trait Request extends js.Object {
  def body: String = js.native
  def cookies: Any = js.native
  def error: String = js.native
  def error_code: Int = js.native
  def headers: js.Dictionary[String] = js.native
  def status: Int = js.native
  def status_text: String = js.native
  def url: String = js.native
  def json(selector: String): js.Object = js.native
}
