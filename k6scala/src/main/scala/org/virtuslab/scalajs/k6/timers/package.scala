package org.virtuslab.scalajs.k6

import scala.concurrent.duration.FiniteDuration

package object timers {

  def setTimeout(callback: => Unit, delay: FiniteDuration): TimerId =
    Timers.setTimeout(() => callback, delay.toMillis)

  def setInterval(callback: => Unit, interval: FiniteDuration): IntervalId =
    Timers.setInterval(() => callback, interval.toMillis)

  def clearTimeout(timerId: TimerId): Unit =
    Timers.clearTimeout(timerId)

  def clearInterval(timerId: IntervalId): Unit =
    Timers.clearInterval(timerId)
}
