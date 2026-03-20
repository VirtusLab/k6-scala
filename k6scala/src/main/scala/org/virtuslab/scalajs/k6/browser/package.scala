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

/**
 * Browser automation via [`k6/browser`](https://grafana.com/docs/k6/latest/javascript-api/k6-browser/)
 * (Chromium-based; see k6 docs for feature availability).
 *
 * Methods delegate to [[Browser]] so you can call `newPage()` at package scope after
 * `import org.virtuslab.scalajs.k6.browser._`, similar to `http.get` vs the native `Http` object.
 */
package object browser {

  import scala.scalajs.js

  def newContext(
      options: Option[BrowserContextOptions] = None
  ): js.Promise[BrowserContext] =
    Browser.newContext(options)

  def newPage(
      options: Option[BrowserContextOptions] = None
  ): js.Promise[Page] =
    Browser.newPage(options)

  def closeContext(): js.Promise[Unit] =
    Browser.closeContext()

  def contextOption: Option[BrowserContext] =
    Browser.contextOption

  def isConnected: Boolean =
    Browser.isConnected

  def version(): js.Promise[String] =
    Browser.version()
}
