//> using scala "3.5.0"
//> using jsModuleKind "es"
//> using dep "org.virtuslab::k6-scala::dev"

/*
 * Example: k6/data SharedArray usage from Scala.
 *
 * Run (after `sbt publishLocalDev`):
 *   scala-cli --power package examples/api-examples/k6-shared-array.scala --js -o shared-array.js
 *   k6 run shared-array.js
 */

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel

import org.virtuslab.scalajs.k6._
import org.virtuslab.scalajs.k6.data._
import org.virtuslab.scalajs.k6.http._
import org.virtuslab.scalajs.k6.options._

@JSExportTopLevel("options")
val options: Options = Options(
  vus = Some(1),
  duration = Some("1s")
)

// Shared test data — extend js.Object so fields become plain JS own properties.
// Scala case classes have mangled internal field names in JS; k6 SharedArray serialisation
// only preserves own properties, so plain case classes would lose their getter methods.
class User(val id: Int, val name: String, val email: String) extends js.Object

// SharedArray is initialised once during the k6 init stage and shared across all VUs.
private val sharedUsers: SharedArray[User] =
  sharedArrayFromSeq("users") {
    Seq(
      new User(1, "Alice", "alice@example.com"),
      new User(2, "Bob", "bob@example.com"),
      new User(3, "Carol", "carol@example.com")
    )
  }

@JSExportTopLevel("default")
def main(): Unit = {
  val firstUser = sharedUsers(0)

  check(
    http.get("https://test.k6.io"),
    Checkers(
      "has shared user name" -> Check[Response](r => firstUser.name.nonEmpty)
    )
  )
}
