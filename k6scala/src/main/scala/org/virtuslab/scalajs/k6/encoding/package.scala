package org.virtuslab.scalajs.k6

package object encoding {
  
  def encoding(
    input: String,
    encoding: Base64Variant,
    format: Format
  ): String = Encoding.b64decode(input, encoding, format)
}
