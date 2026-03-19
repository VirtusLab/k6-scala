//> using scala "3.5.0"
//> using platform "scala-js"
//> using jsModuleKind "esmodule"
//> using jsEmitSourceMaps true
//> using jsNoOpt true
//> using dep "org.virtuslab::k6-jslib::dev"
//> using dep "org.virtuslab::k6-scala::dev"

import scala.scalajs.js
import scala.scalajs.js.annotation._

import org.virtuslab.scalajs.k6.*
import org.virtuslab.scalajs.k6.options.*
import org.virtuslab.scalajs.k6.jslib.utils.RandomUtils

@JSExportTopLevel("options")
val options: Options = Options(vus = Some(1), iterations = Some(1))

@JSExportTopLevel(JSImport.Default)
def main(): Unit = {
  val intBetween = RandomUtils.randomIntBetween(1, 100)
  check(
    intBetween >= 1 && intBetween <= 100,
    Checkers(
      "randomIntBetween(min,max) stays within bounds" -> Check[Boolean](v => v)
    )
  )

  val intExact = RandomUtils.randomIntBetween(5, 5)
  check(
    intExact,
    Checkers(
      "randomIntBetween(5,5) returns 5" -> Check[Int](v => v == 5)
    )
  )

  val items = Seq("a", "b", "c")
  val item = RandomUtils.randomItem(items)
  check(
    items.contains(item),
    Checkers(
      "randomItem(array) returns an element from input" -> Check[Boolean](v => v)
    )
  )

  val emptyItemOpt =
    scala.util.Try(RandomUtils.randomItem[String](Seq.empty[String])).toOption
  check(
    emptyItemOpt.isEmpty,
    Checkers(
      "randomItem(empty) throws or yields no meaningful value" -> Check[Boolean](v => v)
    )
  )

  val s10 = RandomUtils.randomString(10)
  check(
    s10,
    Checkers(
      "randomString(10) has expected length" -> Check[String](v => v.length == 10)
    )
  )

  val charset = "abc"
  val s10Charset = RandomUtils.randomString(10, Some(charset))
  check(
    s10Charset.forall(ch => charset.indexOf(ch) >= 0),
    Checkers(
      "randomString(10,charset) uses only allowed characters" -> Check[Boolean](v => v)
    )
  )

  val s0 = RandomUtils.randomString(0)
  check(
    s0,
    Checkers(
      "randomString(0) returns empty string" -> Check[String](v => v.isEmpty)
    )
  )
}

