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

package org.virtuslab.scalajs.k6

import scala.scalajs.js

import org.virtuslab.scalajs.k6.secrets.SecretSource
import org.virtuslab.scalajs.k6.secrets.Secrets

/**
 * Scala-idiomatic entry points for the `k6/secrets` module.
 *
 * These helpers wrap the underlying JavaScript API while keeping the async
 * `js.Promise` return type so they integrate naturally with k6's async/await
 * execution model.
 *
 * '''Important — CommonJS module kind required:''' This module requires
 * `CommonJSModule` (sbt) or `--js-module-kind commonjs` (scala-cli). The
 * `k6/secrets` module only exposes its API through a default export, and k6's
 * ESM runtime does not correctly surface `namespace.default` for the
 * `import * as` statements that Scala.js generates. See [[Secrets]] for details.
 */
package object secrets {

  /**
   * Retrieve a secret value by name from the default secret source.
   *
   * Example:
   * {{{
   * val apiKeyPromise: js.Promise[String] =
   *   secrets.get("api_key")
   * }}}
   *
   * Behaviour notes (k6 runtime):
   *   - If no default secret source is configured, k6 throws an exception.
   *   - If the secret name does not exist, k6 throws an exception.
   *   - Secret values are redacted in k6 logs and output.
   */
  def get(name: String): js.Promise[String] =
    Secrets.get(name)

  /**
   * Obtain a handle to a named secret source (for example, "vault") and use it
   * to retrieve secrets.
   *
   * Example:
   * {{{
   * val vault: SecretSource =
   *   secrets.source("vault")
   *
   * val tokenPromise: js.Promise[String] =
   *   vault.get("auth_token")
   * }}}
   */
  def source(identifier: String): SecretSource =
    Secrets.source(identifier)
}
