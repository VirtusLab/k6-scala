//> using scala "3.5.0"
//> using jsVersion "1.18.1"
//> using platform scala-js
//> using dep "org.virtuslab::k6-scala::dev"

package example

import scala.scalajs.js
import scala.scalajs.js.annotation.*

import org.virtuslab.scalajs.k6.*
import org.virtuslab.scalajs.k6.http
import org.virtuslab.scalajs.k6.http.Response
import org.virtuslab.scalajs.k6.http.ResponseType
import org.virtuslab.scalajs.k6.http.Params
import org.virtuslab.scalajs.k6.options.*
import org.virtuslab.scalajs.k6.secrets.*

/**
 * E2E example: k6/secrets module.
 *
 * Demonstrates retrieving a secret from the default secret source and using it
 * as an HTTP header value. To run this example, configure a secret source and
 * provide the corresponding secret via k6's `--secret-source` CLI flag.
 *
 * Run with:
 * {{{
 * scripts/run-scala-cli-example.sh k6-secrets
 * }}}
 *
 * Important: this module requires CommonJS module kind (`--js-module-kind commonjs`).
 * k6's ESM runtime does not correctly surface the default export of `k6/secrets`
 * through namespace imports, which Scala.js always generates in ESM mode.
 *
 * Behaviour notes:
 *   - If the secret does not exist, or no secret source is configured, k6 will
 *     fail the test at runtime with an error.
 *   - Secret values are redacted in k6 logs and output.
 */
object SecretsExample {

  @JSExportTopLevel(JSImport.Default)
  def main(): js.Promise[Unit] =
    get("cool")
      .`then`[Unit] { (apiKey: String) =>
        val params = Params(
          headers = Some(Map("Authorization" -> s"Bearer $apiKey")),
          responseType = Some(ResponseType.Text),
          tags = None,
          timeout = None,
          responseCallback = None,
          jar = None,
          compression = None,
          redirects = None,
          auth = None
        )

        val response = http.get("https://test-api.k6.io/public/crocodiles/", Some(params))

        check(
          response,
          Checkers(
            "secrets api key non-empty" ->
              Check[Response](_ => apiKey.nonEmpty),
            "secrets http status is 200" ->
              Check[Response](_.status == 200)
          )
        )

        ()
      }

  @JSExportTopLevel("options")
  val options: Options = Options(
    vus = Some(1),
    iterations = Some(1)
  )
}

