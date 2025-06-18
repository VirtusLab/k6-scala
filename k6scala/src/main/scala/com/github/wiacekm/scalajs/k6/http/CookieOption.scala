package com.github.wiacekm.scalajs.k6.http

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.Date
import scala.concurrent.duration.FiniteDuration

@js.native
trait CookieOption extends js.Object {
  def domain: js.UndefOr[String] = js.native
  def path: js.UndefOr[String] = js.native
  def expires: js.UndefOr[String] = js.native
  def max_age: js.UndefOr[Int] = js.native
  def secure: js.UndefOr[Boolean] = js.native
  def http_only: js.UndefOr[Boolean] = js.native
}

object CookieOption {
  def apply(
      domain: Option[String] = None,
      path: Option[String] = None,
      expires: Option[Date] = None,
      max_age: Option[FiniteDuration] = None,
      secure: Option[Boolean] = None,
      http_only: Option[Boolean] = None
  ): CookieOption =
    js.Dynamic
      .literal(
        domain = domain.orUndefined,
        path = path.orUndefined,
        expires = expires.map(_.toISOString()).orUndefined,
        max_age = max_age.map(_.toSeconds.toInt).orUndefined,
        secure = secure.orUndefined,
        http_only = http_only.orUndefined
      )
      .asInstanceOf[CookieOption]

}
