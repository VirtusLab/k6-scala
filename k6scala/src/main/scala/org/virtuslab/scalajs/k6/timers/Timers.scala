package org.virtuslab.scalajs.k6.timers

import scala.scalajs.js
import scala.scalajs.js.annotation._

@js.native
trait TimerId extends js.Any

@js.native
trait IntervalId extends js.Any

@js.native
@JSImport("k6/timers", JSImport.Namespace)
object Timers extends js.Object {
  def setTimeout(callback: js.Function, delay: Long): TimerId = js.native
  def setInterval(callback: js.Function, interval: Long): IntervalId = js.native
  def clearTimeout(timerId: TimerId): Unit = js.native
  def clearInterval(intervalId: IntervalId): Unit = js.native
}
