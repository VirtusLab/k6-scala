package com.github.wiacekm.scalajs.k6.http

import scala.scalajs.js

@js.native
trait CookieJar extends js.Object {
  def clear(url: String): Unit = js.native
  def cookiesForURL(url: String): CookieJarCookies = js.native
  def set(url: String, name: String, value: String): Unit = js.native
  def set(url: String, name: String, value: String, options: CookieOption): Unit = js.native
  def delete(url: String, name: String): Unit = js.native
}
