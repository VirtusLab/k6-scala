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

/**
 * Represents a specific secret source configured in k6, such as a named vault
 * or other external secret provider.
 *
 * Instances are obtained via `secrets.source(identifier)` and expose a
 * single `get` method for retrieving secret values asynchronously.
 *
 * Modelled as a trait (not a class) because `SecretSource` is not a named
 * export of the `k6/secrets` module — it is only returned by `source()`.
 */
@js.native
trait SecretSource extends js.Object {

  /**
   * Retrieve a secret value by name from this secret source.
   *
   * Behaviour notes (k6 runtime):
   *   - If the secret with the given name does not exist, k6 throws an exception.
   *   - Returned values are redacted in k6 logs and output.
   */
  def get(name: String): js.Promise[String] = js.native
}
