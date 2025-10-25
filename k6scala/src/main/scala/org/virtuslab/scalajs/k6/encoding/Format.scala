package org.virtuslab.scalajs.k6.encoding

import scala.scalajs.js

sealed trait Format extends js.Any

object Format {
  val S: Format = "s".asInstanceOf[Format]
}
