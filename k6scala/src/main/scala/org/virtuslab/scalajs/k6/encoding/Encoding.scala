package org.virtuslab.scalajs.k6.encoding

import scala.scalajs.js
import scala.scalajs.js.|
import scala.scalajs.js.annotation._
import scala.scalajs.js.typedarray.ArrayBuffer

@js.native
@JSImport("k6/encoding", JSImport.Namespace)
object Encoding extends js.Object {

  /**
   * Base64 decode a string.
   * @param input The string to base64 decode.
   * @param encoding Base64 variant (optional).
   * @return The base64 decoded version of the input string as ArrayBuffer.
   */
  def b64decode(input: String, encoding: js.UndefOr[Base64Variant] = js.undefined): ArrayBuffer =
    js.native

  /**
   * Base64 decode a string to string format.
   * @param input The string to base64 decode.
   * @param encoding Base64 variant.
   * @param format Must be "s" to return string format.
   * @return The base64 decoded version of the input string as String.
   */
  def b64decode(input: String, encoding: Base64Variant, format: "s"): String = js.native

  /**
   * Base64 encode a string or ArrayBuffer.
   * @param input String to encode or ArrayBuffer object.
   * @param encoding Base64 variant (optional).
   * @return Base64 encoded string.
   */
  def b64encode(
      input: String | ArrayBuffer,
      encoding: js.UndefOr[Base64Variant] = js.undefined
  ): String = js.native
}

/**
 * Base64 variant type.
 */
object Base64Variant {
  val Std: Base64Variant = "std".asInstanceOf[Base64Variant]
  val RawStd: Base64Variant = "rawstd".asInstanceOf[Base64Variant]
  val Url: Base64Variant = "url".asInstanceOf[Base64Variant]
  val RawUrl: Base64Variant = "rawurl".asInstanceOf[Base64Variant]
}

sealed trait Base64Variant extends js.Any
