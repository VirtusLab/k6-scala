package org.scalajs.examples.helloworld

import scala.scalajs.js.annotation._
import com.github.wiacekm.scalajs.k6.http
import com.github.wiacekm.scalajs.k6.http._
import com.github.wiacekm.scalajs.k6._
import scala.concurrent.duration._

object HelloWorldPerfTest {

  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    val url: URL = "https://test.k6.io"
    println("test print")
    // val response = http.get(url)
  //   sleep(1.second)
  //   check(
  //     response,
  //     Checkers(
  //       "status is 200" -> Check[Response](r => r.status == 200)
  //     )
  //   )
  }
}
