//> using scala "3.5.0"
//> using jsVersion "1.18.1"
//> using platform scala-js
//> using dep "org.virtuslab::k6-scala::dev"

package example

import scala.scalajs.js
import scala.scalajs.js.annotation.*
import scala.scalajs.js.typedarray.ArrayBuffer
import scala.scalajs.js.typedarray.Uint8Array

import org.virtuslab.scalajs.k6.*
import org.virtuslab.scalajs.k6.webcrypto.*
import org.virtuslab.scalajs.k6.http.*
import org.virtuslab.scalajs.k6.options.*

/**
 * E2E example: global Web Crypto API via the `crypto` object.
 *
 * Demonstrates UUID generation, random byte filling, and AES-GCM
 * encrypt/decrypt round-trip using the Scala.js facade.
 */
object WebCryptoExample {

  @JSExportTopLevel(JSImport.Default)
  def main(): js.Promise[Unit] = {
    val uuid = randomUUID()
    val randomBytes = new Uint8Array(16)
    getRandomValues(randomBytes)

    val plaintext = new Uint8Array(4)
    plaintext(0) = 1
    plaintext(1) = 2
    plaintext(2) = 3
    plaintext(3) = 4

    val keyUsages = Seq(KeyUsage.Encrypt, KeyUsage.Decrypt)

    subtle
      .generateKey(
        AesKeyGenParams.aesGcm(256),
        extractable = true,
        KeyUsage.toJsArray(keyUsages)
      )
      .`then`[Unit](
        { (keyAny: js.Any) =>
          val key = keyAny.asInstanceOf[CryptoKey]

          val iv = new Uint8Array(12)
          getRandomValues(iv)
          val params = AesGcmParams(iv)

          subtle
            .encrypt(params, key, plaintext)
            .`then`[Unit](
              { (ciphertext: ArrayBuffer) =>
                subtle
                  .decrypt(params, key, ciphertext)
                  .`then`[Unit](
                    { (decrypted: ArrayBuffer) =>
                      check(
                        (),
                        Checkers(
                          "webcrypto roundtrip succeeded" ->
                            Check[Unit](_ => decrypted.byteLength == plaintext.byteLength),
                          "webcrypto uuid non-empty" ->
                            Check[Unit](_ => uuid.nonEmpty),
                          "webcrypto random filled" ->
                            Check[Unit](_ => randomBytes.byteLength == 16)
                        )
                      )
                      ()
                    }
                  )
                ()
              }
            )
        }
      )
  }

  @JSExportTopLevel("options")
  val options: Options = Options(
    vus = Some(1),
    iterations = Some(1)
  )
}
