package com.github.wiacekm.scalajs.k6.options

sealed trait PolicyType {
  def asString: String
}

object PolicyType {
  case object PreferIPv4 extends PolicyType {
    def asString: String = "preferIPv4"
  }
  case object PreferIPv6 extends PolicyType {
    def asString: String = "preferIPv6"
  }
  case object OnlyIPv4 extends PolicyType {
    def asString: String = "onlyIPv4"
  }
  case object OnlyIPv6 extends PolicyType {
    def asString: String = "onlyIPv6"
  }
  case object Any extends PolicyType {
    def asString: String = "any"
  }
}
