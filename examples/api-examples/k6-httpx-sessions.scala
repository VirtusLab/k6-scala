//> using scala "3.5.0"
//> using platform "scala-js"
//> using jsModuleKind "esmodule"
//> using jsEmitSourceMaps true
//> using jsNoOpt true
//> using dep "org.virtuslab::k6-jslib::dev"
//> using dep "org.virtuslab::k6-scala::dev"

import scala.scalajs.js
import scala.scalajs.js.annotation._

import org.virtuslab.scalajs.k6._
import org.virtuslab.scalajs.k6.http.*
import org.virtuslab.scalajs.k6.options.Options
import org.virtuslab.scalajs.k6.jslib.httpx.Session
import org.virtuslab.scalajs.k6.jslib.utils.RandomUtils

@JSExportTopLevel("options")
val options: Options = Options(vus = Some(1), iterations = Some(1))

@JSExportTopLevel(JSImport.Default)
def main(): Unit = {
  val username = s"user${RandomUtils.randomIntBetween(1, 100_000)}@example.com"
  val password = "secretpassword"

  val session = Session(
    baseURL = Some("https://quickpizza.grafana.com"),
    headers = Map("User-Agent" -> "k6-scala-httpx-session")
  )

  val registrationPayload = js.JSON.stringify(
    js.Dynamic.literal(
      username = username,
      password = password
    )
  )
  val registrationResp = session.post("/api/users", body = Some(registrationPayload))
  check(
    registrationResp,
    Checkers(
      "registration status is 201" -> Check[Response](r => r.status == 201)
    )
  )

  val loginPayload = js.JSON.stringify(
    js.Dynamic.literal(
      username = username,
      password = password
    )
  )
  val loginResp = session.post("/api/users/token/login", body = Some(loginPayload))
  check(
    loginResp,
    Checkers(
      "login status is 200" -> Check[Response](r => r.status == 200)
    )
  )

  val token = loginResp.json("token").asInstanceOf[String]
  session.addHeader("Authorization", s"Bearer $token")
  session.addTag("flow", "httpx-session")

  val createRatingResp = session.post(
    "/api/ratings",
    body = Some(
      js.JSON.stringify(
        js.Dynamic.literal(stars = 5, pizza_id = 1)
      )
    )
  )
  check(
    createRatingResp,
    Checkers(
      "rating creation status is 201" -> Check[Response](r => r.status == 201)
    )
  )

  // Edge-case exercise: empty base URL should preserve absolute URLs.
  val noBaseUrlSession = Session(baseURL = Some(""))
  val absoluteUrlResp = noBaseUrlSession.get("https://quickpizza.grafana.com/api/pizza")
  check(
    absoluteUrlResp,
    Checkers(
      "empty base URL keeps absolute URL requests valid" -> Check[Response](r =>
        r.status == 200
      )
    )
  )
}
