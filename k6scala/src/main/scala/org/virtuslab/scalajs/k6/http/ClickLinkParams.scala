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

package org.virtuslab.scalajs.k6.http

import scala.scalajs.js
import scala.scalajs.js.JSConverters._

/**
 * Parameters for [[org.virtuslab.scalajs.k6.http.Response.clickLink Response.clickLink()]].
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-http/response/response-clicklink/ k6
 *   Response.clickLink()]]
 */
@js.native
trait ClickLinkParams extends js.Object {

  /** CSS selector to locate the link element. Defaults to `"a[href]"` in k6. */
  def selector: js.UndefOr[String] = js.native

  /** HTTP request parameters (headers, tags, etc.) forwarded to the resulting request. */
  def params: js.UndefOr[Params] = js.native
}

object ClickLinkParams {

  def apply(
      selector: Option[String] = None,
      params: Option[Params] = None
  ): ClickLinkParams =
    js.Dynamic
      .literal(
        selector = selector.orUndefined,
        params = params.orUndefined
      )
      .asInstanceOf[ClickLinkParams]
}
