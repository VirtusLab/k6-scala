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
import org.virtuslab.scalajs.k6.html.Selection

@js.native
trait Response extends js.Object {
  def body: String = js.native
  def cookies: js.Dictionary[ResponseCookie] = js.native
  def error_code: Int = js.native
  def headers: js.Dictionary[String] = js.native

  /**
   * Parses the response body as HTML and returns a [[Selection]] wrapping the entire document.
   *
   * Equivalent to `parseHTML(response.body)`.
   *
   * @return
   *   a [[Selection]] representing the parsed HTML
   * @see
   *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-http/response/response-html/ k6
   *   Response.html()]]
   */
  def html(): Selection = js.native

  /**
   * Parses the response body as HTML and returns a [[Selection]] scoped to the given CSS selector.
   *
   * Equivalent to `parseHTML(response.body).find(selector)`.
   *
   * @param selector
   *   a CSS selector expression
   * @return
   *   a [[Selection]] containing only the matched elements
   */
  def html(selector: String): Selection = js.native

  /**
   * Parses the response body as JSON and returns the resulting JS value.
   *
   * The result is cached internally by k6 — multiple calls return the same object. Throws a k6
   * runtime error if the body is not valid JSON.
   *
   * @return
   *   the deserialized JS value (`String`, `Double`, `Boolean`, `js.Array`, `js.Object`, or
   *   `null`)
   */
  def json(): js.Any = js.native

  /**
   * Parses the response body as JSON and extracts a nested value using gjson path syntax.
   *
   * The result is cached internally by k6 — the full JSON parse happens once. Returns `null` when
   * the selector matches no value.
   *
   * @param selector
   *   a [[https://github.com/tidwall/gjson#path-syntax gjson path expression]], e.g.
   *   `"user.name"` or `"items.#.id"`
   * @return
   *   the extracted JS value, or `null` if the path does not match
   */
  def json(selector: String): js.Any = js.native

  def ocsp: OCSP = js.native
  def proto: ProtocolType
  def remote_ip: String
  def remote_port: Int
  def request: ResponseRequest = js.native
  def status: Int = js.native
  def status_text: String = js.native

  /**
   * Finds the first `<form>` element in the response body, collects all form fields, and submits
   * an HTTP request using the form's `action` and `method` attributes. Returns the resulting
   * [[Response]].
   *
   * Throws a k6 runtime error if no `<form>` element is found.
   *
   * @see
   *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-http/response/response-submitform/
   *   k6 Response.submitForm()]]
   */
  def submitForm(): Response = js.native

  /**
   * Finds a form element matching the supplied parameters, collects form fields (with optional
   * overrides), and submits an HTTP request. Returns the resulting [[Response]].
   *
   * @param params
   *   options controlling form selection and field overrides
   * @see
   *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-http/response/response-submitform/
   *   k6 Response.submitForm()]]
   */
  def submitForm(params: SubmitFormParams): Response = js.native

  /**
   * Finds the first `<a href>` element in the response body and performs a GET request to its
   * `href`. Returns the resulting [[Response]].
   *
   * Throws a k6 runtime error if no matching link is found.
   *
   * @see
   *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-http/response/response-clicklink/ k6
   *   Response.clickLink()]]
   */
  def clickLink(): Response = js.native

  /**
   * Finds a link matching the given parameters and performs a GET request to its `href`. Returns
   * the resulting [[Response]].
   *
   * @param params
   *   options controlling link selection and HTTP request parameters
   * @see
   *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-http/response/response-clicklink/ k6
   *   Response.clickLink()]]
   */
  def clickLink(params: ClickLinkParams): Response = js.native

  /**
   * Timing breakdown for the HTTP request/response cycle.
   *
   * Contains `blocked`, `connecting`, `tls_handshaking`, `sending`, `waiting`, `receiving`, and
   * `duration` fields (all `Double`, milliseconds).
   */
  def timings: ResponseTimings = js.native

  def tls_cipher_suite: CipherSuiteType = js.native
  def tls_version: String = js.native
  def url: String = js.native
}
