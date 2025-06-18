package com.github.wiacekm.scalajs.k6.options

import scala.scalajs.js
import scala.scalajs.js.JSConverters._

@js.native
trait Certificate extends js.Object {
  val cert: String = js.native
  val domains: js.UndefOr[String] = js.native
  val key: String = js.native
  val password: js.UndefOr[String] = js.native
}

object Certificate {
  def apply(
      cert: String,
      key: String,
      domains: Option[Boolean] = None,
      password: Option[String] = None
  ): Certificate =
    js.Dynamic
      .literal(
        cert = cert,
        key = key,
        domains = domains.orUndefined,
        password = password.orUndefined
      )
      .asInstanceOf[Certificate]
}
