//> using scala "3.5.0"
//> using jsVersion "1.18.1"
//> using platform scala-js
//> using dep "org.virtuslab::k6-scala::dev"

package example

import scala.scalajs.js
import scala.scalajs.js.JSConverters.*
import scala.scalajs.js.annotation.*

import org.virtuslab.scalajs.k6.*
import org.virtuslab.scalajs.k6.crypto.*
import org.virtuslab.scalajs.k6.http.*
import org.virtuslab.scalajs.k6.options.*

/**
 * E2E example: k6/crypto operations.
 *
 * Demonstrates hashing, HMAC, streaming hash computation, and random byte generation using the
 * Scala facade for the `k6/crypto` module.
 */
object CryptoOperationsExample {

  @JSExportTopLevel(JSImport.Default)
  def main(): Unit = {
    val data = "hello, k6-scala"
    val secret = "super-secret"

    val md5Hex = md5(data)
    val sha256B64 = sha256(data, OutputEncoding.Base64)

    val hmacSha256Hex =
      hmac(HashAlgorithm.SHA256, secret, data, OutputEncoding.Hex)

    val streamingDigest = {
      val hasher = createHash(HashAlgorithm.SHA256)
      hasher.update("chunk-1:")
      hasher.update("chunk-2")
      hasher.digest("hex")
    }

    val random = randomBytes(16)

    check(
      (),
      Checkers(
        "crypto digests look sane" ->
          Check[Unit](_ =>
            md5Hex.length == 32 &&
              sha256B64.nonEmpty &&
              hmacSha256Hex.length == 64 &&
              streamingDigest.nonEmpty &&
              random.byteLength == 16
          )
      )
    )
  }

  @JSExportTopLevel("options")
  val options: Options = Options(
    vus = Some(1),
    iterations = Some(1),
    thresholds = Some(
      Map(
        "http_reqs" -> Seq("count >= 0").toJSArray
      ).toJSDictionary
        .asInstanceOf[js.Dictionary[js.Array[String | ObjectThreshold]]]
    )
  )
}
