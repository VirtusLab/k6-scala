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

package org.virtuslab.scalajs.k6.secrets

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

/**
 * Native facade for the `k6/secrets` module.
 *
 * This mirrors the JavaScript API surface and is kept package-private. Public,
 * Scala-idiomatic entry points are defined in the `org.virtuslab.scalajs.k6.secrets`
 * package object.
 *
 * '''Important — CommonJS module kind required:''' The `k6/secrets` module only
 * exposes `get` and `source` through its default export. k6's ESM runtime does not
 * populate `namespace.default` for `import * as X` statements (which Scala.js always
 * generates in ESM mode), so the facade resolves to `undefined` under ESM. Use
 * `CommonJSModule` (sbt) or `--js-module-kind commonjs` (scala-cli) to work around
 * this k6 limitation.
 *
 * Behaviour notes (k6 runtime):
 *   - `get(name)` resolves the secret value from the configured default secret source.
 *     If no default source is configured, k6 throws an exception.
 *   - `source(identifier)` selects a named secret source (for example, "vault") that
 *     also exposes a `get(name)` function.
 *   - Secret values are redacted in k6 logs and output; values will typically appear
 *     as `***SECRET_REDACTED***` when printed.
 */
@js.native
@JSImport("k6/secrets", JSImport.Default)
private[secrets] object Secrets extends js.Object {

  /** Retrieve a secret from the default secret source. */
  def get(name: String): js.Promise[String] = js.native

  /** Select a named secret source (for example, "vault") and access its secrets. */
  def source(identifier: String): SecretSource = js.native
}
