/*
 * Copyright 2024 VirtusLab
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.virtuslab.scalajs.k6.utils

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
