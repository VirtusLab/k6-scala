package com.github.wiacekm.scalajs.k6.http

import scala.scalajs.js

@js.native
trait ProtocolType extends js.Any

sealed trait Protocol {

  def asType: ProtocolType
}

object Protocol {
  // TODO: rewrite toString
  case object `HTTP/1.0` extends Protocol {
    override def asType: ProtocolType = "HTTP/1.0".asInstanceOf[ProtocolType]
  }
  case object `HTTP/1.1` extends Protocol {
    override def asType: ProtocolType = "HTTP/1.1".asInstanceOf[ProtocolType]
  }
  case object `HTTP/2.0` extends Protocol {
    override def asType: ProtocolType = "HTTP/2.0".asInstanceOf[ProtocolType]
  }
}
