//> using scala "3.5.0"
//> using platform "scala-js"
//> using jsModuleKind "esmodule"
//> using jsEmitSourceMaps true
//> using jsNoOpt true
//> using dep "org.virtuslab::k6-jslib::dev"

import scala.scalajs.js
import scala.scalajs.js.annotation._
import js.JSConverters._

import org.virtuslab.scalajs.k6.jslib.utils.RandomUtils

@js.native
private trait Check[T] extends js.Function1[T, Boolean]

private object Check {
  def apply[T](f: T => Boolean): Check[T] = f.asInstanceOf[Check[T]]
}

@js.native
@JSImport("k6", JSImport.Namespace)
private object K6 extends js.Object {
  def check[T](
      response: T,
      sets: js.Dictionary[Check[T]],
      tags: js.UndefOr[js.Dictionary[String]] = js.undefined
  ): Boolean = js.native
}

private def check[T](response: T, sets: Map[String, T => Boolean]): Boolean =
  K6.check(response, sets.map { case (k, f) => k -> Check(f) }.toJSDictionary)

@JSExportTopLevel("options")
val options: js.Any = js.Dynamic.literal(vus = 1, duration = "1s")

@JSExportTopLevel(JSImport.Default)
def main(): Unit = {
  val intBetween = RandomUtils.randomIntBetween(1, 100)
  check(
    intBetween >= 1 && intBetween <= 100,
    Map(
      "randomIntBetween(min,max) stays within bounds" -> ((v: Boolean) => v)
    )
  )

  val intExact = RandomUtils.randomIntBetween(5, 5)
  check(
    intExact,
    Map(
      "randomIntBetween(5,5) returns 5" -> ((v: Int) => v == 5)
    )
  )

  val items = Seq("a", "b", "c")
  val item = RandomUtils.randomItem(items)
  check(
    items.contains(item),
    Map(
      "randomItem(array) returns an element from input" -> ((v: Boolean) => v)
    )
  )

  val emptyItemOpt =
    scala.util.Try(RandomUtils.randomItem[String](Seq.empty[String])).toOption
  check(
    emptyItemOpt.isEmpty,
    Map(
      "randomItem(empty) throws or yields no meaningful value" -> ((v: Boolean) => v)
    )
  )

  val s10 = RandomUtils.randomString(10)
  check(
    s10,
    Map(
      "randomString(10) has expected length" -> ((v: String) => v.length == 10)
    )
  )

  val charset = "abc"
  val s10Charset = RandomUtils.randomString(10, Some(charset))
  check(
    s10Charset.forall(ch => charset.indexOf(ch) >= 0),
    Map(
      "randomString(10,charset) uses only allowed characters" -> ((v: Boolean) => v)
    )
  )

  val s0 = RandomUtils.randomString(0)
  check(
    s0,
    Map(
      "randomString(0) returns empty string" -> ((v: String) => v.isEmpty)
    )
  )
}

