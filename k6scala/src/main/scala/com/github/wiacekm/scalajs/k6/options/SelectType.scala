package com.github.wiacekm.scalajs.k6.options

sealed trait SelectType {
  def asString: String
}

object SelectType {
  case object First extends SelectType {
    def asString: String = "first"
  }
  case object Random extends SelectType {
    def asString: String = "random"
  }
  case object RoundRobin extends SelectType {
    def asString: String = "roundRobin"
  }
}
