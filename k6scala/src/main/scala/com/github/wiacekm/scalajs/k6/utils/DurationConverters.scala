package com.github.wiacekm.scalajs.k6.utils

import scala.concurrent.duration._

object DurationConverters {
  implicit class RichDuration(val d: FiniteDuration) extends AnyVal {
    def toK6: String = toK6Duration(d)

    private def toK6Duration(fd: FiniteDuration): String = {

      // Compose using largest units: h, m, s, ms, µs, ns
      val sb = new StringBuilder
      var rest = fd

      def appendUnit(unit: FiniteDuration, suffix: String): Unit = {
        val count = (rest / unit).toLong
        if (count > 0) {
          sb.append(count).append(suffix)
          rest = rest - unit * count
        }
      }

      appendUnit(1.hour, "h")
      appendUnit(1.minute, "m")
      appendUnit(1.second, "s")
      appendUnit(1.millisecond, "ms")
      appendUnit(1.microsecond, "us")
      appendUnit(1.nanosecond, "ns")

      if (sb.isEmpty)
        "0s"
      else
        sb.toString()
    }
  }
}
