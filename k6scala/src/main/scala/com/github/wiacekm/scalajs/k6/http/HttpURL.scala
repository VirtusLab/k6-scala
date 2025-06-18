package com.github.wiacekm.scalajs.k6.http

import scala.scalajs.{js => js}
import scala.scalajs.js.JSConverters._

@js.native
trait HttpURL extends js.Object {
  val href: String
}

object HttpURL {
  implicit class UrlInterpolator(val sc: StringContext) extends AnyVal {
    def url(args: Any*): HttpURL = {
      Http.url(sc.parts.toJSArray, args.toJSArray)
    }
  }
}
