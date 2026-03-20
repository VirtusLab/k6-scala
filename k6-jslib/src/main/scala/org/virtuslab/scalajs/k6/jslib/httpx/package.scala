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

package org.virtuslab.scalajs.k6.jslib

/**
 * Stateful HTTP sessions from jslib [httpx](https://grafana.com/docs/k6/latest/javascript-api/jslib/httpx/).
 *
 * [[session]] delegates to [[Session]].apply so you can construct a client at package scope after
 * `import org.virtuslab.scalajs.k6.jslib.httpx._`, similar to `http.get` wrapping the native module.
 */
package object httpx {

  import scala.scalajs.js

  /** Same as [[Session.apply]] — httpx session with optional base URL, headers, tags, and k6 params. */
  def session(
      baseURL: Option[String] = None,
      headers: Map[String, String] = Map.empty,
      tags: Map[String, String] = Map.empty,
      k6Params: Option[js.Object] = None
  ): Session =
    Session(baseURL, headers, tags, k6Params)
}
