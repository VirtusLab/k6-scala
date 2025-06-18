package com.github.wiacekm.scalajs.k6.http

import scala.scalajs.js

@js.native
trait ResponseCookie extends js.Object {

  def name: String = js.native
  def value: String = js.native
  def domain: String = js.native
  def path: String = js.native
  def httpOnly: String = js.native
  def secure: String = js.native
  def maxAge: String = js.native
  def expires: String = js.native
}
