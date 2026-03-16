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

package org.virtuslab.scalajs.k6.browser

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation.JSImport

/**
 * Native facade for the k6/browser module entry point. Provides access to the global `browser`
 * object used for browser-based testing.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-browser/ k6 browser]]
 */
@js.native
@JSImport("k6/browser", JSImport.Namespace)
private[browser] object BrowserNative extends js.Object {

  /** Creates a new [[BrowserContext]] with optional options. */
  def newContext(
      options: js.UndefOr[BrowserContextOptions] = js.undefined
  ): js.Promise[BrowserContext] = js.native

  /**
   * Creates a new page in a new [[BrowserContext]] and returns it. The concrete Page facade is
   * introduced in a later module; for now this is typed as `js.Any`.
   */
  def newPage(
      options: js.UndefOr[BrowserContextOptions] = js.undefined
  ): js.Promise[js.Any] = js.native

  /** Closes the current [[BrowserContext]] and all of its pages. */
  def closeContext(): js.Promise[Unit] = js.native

  /**
   * Returns the current [[BrowserContext]] if one has been created, otherwise `null`.
   *
   * The Scala wrapper exposes this as an `Option[BrowserContext]`.
   */
  def context(): js.UndefOr[BrowserContext] = js.native

  /** Indicates whether the CDP connection to the browser process is active. */
  def isConnected: Boolean = js.native

  /** Returns the browser application version string. */
  def version(): js.Promise[String] = js.native
}

/**
 * Scala wrapper for the k6/browser `browser` module. Provides idiomatic access to
 * [[BrowserNative]] using Scala collections and `Option`.
 */
object Browser {

  def newContext(
      options: Option[BrowserContextOptions] = None
  ): js.Promise[BrowserContext] =
    BrowserNative.newContext(options.orUndefined)

  def newPage(
      options: Option[BrowserContextOptions] = None
  ): js.Promise[js.Any] =
    BrowserNative.newPage(options.orUndefined)

  def closeContext(): js.Promise[Unit] =
    BrowserNative.closeContext()

  def contextOption: Option[BrowserContext] = {
    val raw = BrowserNative.context()
    if (js.isUndefined(raw) || raw == null) None
    else Some(raw.asInstanceOf[BrowserContext])
  }

  def isConnected: Boolean =
    BrowserNative.isConnected

  def version(): js.Promise[String] =
    BrowserNative.version()
}

