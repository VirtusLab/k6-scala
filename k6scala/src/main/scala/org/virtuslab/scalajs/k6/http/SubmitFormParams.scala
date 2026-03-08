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
 * Parameters for [[org.virtuslab.scalajs.k6.http.Response.submitForm Response.submitForm()]].
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-http/response/response-submitform/ k6
 *   Response.submitForm()]]
 */
@js.native
trait SubmitFormParams extends js.Object {

  /** CSS selector to locate the form element. Defaults to `"form"` in k6. */
  def formSelector: js.UndefOr[String] = js.native

  /** Key-value pairs to override form field values before submission. */
  def fields: js.UndefOr[js.Dictionary[String]] = js.native

  /** CSS selector for the submit button, used to determine the form action. */
  def submitSelector: js.UndefOr[String] = js.native
}

object SubmitFormParams {

  def apply(
      formSelector: Option[String] = None,
      fields: Option[Map[String, String]] = None,
      submitSelector: Option[String] = None
  ): SubmitFormParams =
    js.Dynamic
      .literal(
        formSelector = formSelector.orUndefined,
        fields = fields.map(_.toJSDictionary).orUndefined,
        submitSelector = submitSelector.orUndefined
      )
      .asInstanceOf[SubmitFormParams]
}
