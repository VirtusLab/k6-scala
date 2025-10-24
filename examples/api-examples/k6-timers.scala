//> using scala "3.5.0"
//> using jsVersion "1.18.1"
//> using platform scala-js
//> using dep "org.virtuslab::k6-scala::dev"
package example

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import org.virtuslab.scalajs.k6.execution.*
import org.virtuslab.scalajs.k6.timers.*
import org.virtuslab.scalajs.k6.*
import scala.concurrent.duration.*

object Example {
  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    var timeoutId = null.asInstanceOf[TimerId]
    val intervalId = setInterval(println("This runs every 200ms"), 200.milliseconds)
    timeoutId = setTimeout(
      {
        println("This runs after 2s")
        clearInterval(intervalId)
        clearTimeout(timeoutId)
      },
      2.seconds
    )
    println(s"secnario name is: ${Execution.scenario.name}")
  }
}
