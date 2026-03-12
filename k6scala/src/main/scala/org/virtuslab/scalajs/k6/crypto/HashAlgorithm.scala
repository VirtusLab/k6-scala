/*
 * Copyright 2024 VirtusLab
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.virtuslab.scalajs.k6.crypto

/**
 * Supported hash algorithms for the `k6/crypto` module.
 *
 * These values map directly to the algorithm strings expected by k6, for example:
 * {{{
 *   HashAlgorithm.SHA256 // -> "sha256"
 * }}}
 */
sealed trait HashAlgorithm

object HashAlgorithm {

  case object MD4        extends HashAlgorithm
  case object MD5        extends HashAlgorithm
  case object SHA1       extends HashAlgorithm
  case object SHA256     extends HashAlgorithm
  case object SHA384     extends HashAlgorithm
  case object SHA512     extends HashAlgorithm
  case object SHA512_224 extends HashAlgorithm
  case object SHA512_256 extends HashAlgorithm
  case object RIPEMD160  extends HashAlgorithm

  private[crypto] def toJs(algorithm: HashAlgorithm): String =
    algorithm match {
      case MD4        => "md4"
      case MD5        => "md5"
      case SHA1       => "sha1"
      case SHA256     => "sha256"
      case SHA384     => "sha384"
      case SHA512     => "sha512"
      case SHA512_224 => "sha512_224"
      case SHA512_256 => "sha512_256"
      case RIPEMD160  => "ripemd160"
    }
}

