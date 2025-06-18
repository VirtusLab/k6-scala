package com.github.wiacekm.scalajs.k6.http

import scala.scalajs.{js => js}

@js.native
trait FileData extends js.Object {
  def data: Data = js.native
  def content_type: String = js.native
  def filename: String = js.native
}
