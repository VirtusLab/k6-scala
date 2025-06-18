package com.github.wiacekm.scalajs.k6.options

import scala.scalajs.js
import scala.concurrent.duration.FiniteDuration
import com.github.wiacekm.scalajs.k6.utils.DurationConverters._

@js.native
trait DnsOption extends js.Object {
  val ttl: String = js.native
  val select: String = js.native
  val policy: String = js.native
}

object DnsOption {

  def apply(ttl: FiniteDuration, select: SelectType, policy: PolicyType): DnsOption =
    js.Dynamic
      .literal(
        ttl = ttl.toK6,
        select = select.asString,
        policy = policy.asString
      )
      .asInstanceOf[DnsOption]
}
