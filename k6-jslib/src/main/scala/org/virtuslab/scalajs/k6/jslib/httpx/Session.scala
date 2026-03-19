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

package org.virtuslab.scalajs.k6.jslib.httpx

import scala.scalajs.js
import scala.scalajs.js.annotation._
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.|
import org.virtuslab.scalajs.k6.http.Response

/**
 * Stateful HTTP session wrapper for `https://jslib.k6.io/httpx/0.1.0/index.js`.
 *
 * The session keeps defaults (base URL, headers, tags, and k6 request params)
 * and applies them to all requests.
 */
final class Session private (private val native: Session.HttpxNative) {

  def setBaseUrl(baseURL: String): Unit =
    native.setBaseUrl(baseURL)

  def addHeader(key: String, value: String): Unit =
    native.addHeader(key, value)

  def addHeaders(headers: Map[String, String]): Unit =
    native.addHeaders(headers.toJSDictionary)

  def clearHeader(name: String): Unit =
    native.clearHeader(name)

  def addTag(key: String, value: String): Unit =
    native.addTag(key, value)

  def addTags(tags: Map[String, String]): Unit =
    native.addTags(tags.toJSDictionary)

  def clearTag(name: String): Unit =
    native.clearTag(name)

  def request(
      method: String,
      url: Session.Url,
      body: Option[js.Any] = None,
      params: Option[js.Object] = None
  ): Response =
    native.request(method, url, body.orUndefined, params.orUndefined)

  def asyncRequest(
      method: String,
      url: Session.Url,
      body: Option[js.Any] = None,
      params: Option[js.Object] = None
  ): js.Promise[Response] =
    native.asyncRequest(method, url, body.orUndefined, params.orUndefined)

  def get(
      url: Session.Url,
      body: Option[js.Any] = None,
      params: Option[js.Object] = None
  ): Response =
    native.get(url, body.orUndefined, params.orUndefined)

  def post(
      url: Session.Url,
      body: Option[js.Any] = None,
      params: Option[js.Object] = None
  ): Response =
    native.post(url, body.orUndefined, params.orUndefined)

  def put(
      url: Session.Url,
      body: Option[js.Any] = None,
      params: Option[js.Object] = None
  ): Response =
    native.put(url, body.orUndefined, params.orUndefined)

  def patch(
      url: Session.Url,
      body: Option[js.Any] = None,
      params: Option[js.Object] = None
  ): Response =
    native.patch(url, body.orUndefined, params.orUndefined)

  def delete(
      url: Session.Url,
      body: Option[js.Any] = None,
      params: Option[js.Object] = None
  ): Response =
    native.delete(url, body.orUndefined, params.orUndefined)

  def options(
      url: Session.Url,
      body: Option[js.Any] = None,
      params: Option[js.Object] = None
  ): Response =
    native.options(url, body.orUndefined, params.orUndefined)

  def head(
      url: Session.Url,
      body: Option[js.Any] = None,
      params: Option[js.Object] = None
  ): Response =
    native.head(url, body.orUndefined, params.orUndefined)

  def trace(
      url: Session.Url,
      body: Option[js.Any] = None,
      params: Option[js.Object] = None
  ): Response =
    native.trace(url, body.orUndefined, params.orUndefined)

  def batch(requests: Seq[js.Any], sharedParams: Option[js.Object] = None): js.Array[Response] =
    native.batch(requests.toJSArray, sharedParams.orUndefined)
}

object Session {

  type Url = String | HttpUrl

  @js.native
  trait HttpUrl extends js.Object {
    val name: String = js.native
    val url: String = js.native
    val clean_url: String = js.native
  }

  @js.native
  trait SessionOptions extends js.Object

  object SessionOptions {
    def apply(
        baseURL: Option[String] = None,
        headers: Map[String, String] = Map.empty,
        tags: Map[String, String] = Map.empty,
        k6Params: Option[js.Object] = None
    ): SessionOptions = {
      val options = js.Dynamic.literal()
      baseURL.foreach(url => options.updateDynamic("baseURL")(url))
      if (headers.nonEmpty) options.updateDynamic("headers")(headers.toJSDictionary)
      if (tags.nonEmpty) options.updateDynamic("tags")(tags.toJSDictionary)
      k6Params.foreach(params => js.Object.assign(options, params))
      options.asInstanceOf[SessionOptions]
    }
  }

  def apply(
      baseURL: Option[String] = None,
      headers: Map[String, String] = Map.empty,
      tags: Map[String, String] = Map.empty,
      k6Params: Option[js.Object] = None
  ): Session = {
    val options = SessionOptions(
      baseURL = baseURL,
      headers = headers,
      tags = tags,
      k6Params = k6Params
    )
    new Session(new HttpxNative(options))
  }

  @js.native
  @JSImport("https://jslib.k6.io/httpx/0.1.0/index.js", "Httpx")
  private[httpx] class HttpxNative(opts: js.UndefOr[SessionOptions] = js.undefined) extends js.Object {
    def setBaseUrl(baseURL: String): Unit = js.native
    def addHeader(key: String, value: String): Unit = js.native
    def addHeaders(headers: js.Dictionary[String]): Unit = js.native
    def clearHeader(name: String): Unit = js.native
    def addTag(key: String, value: String): Unit = js.native
    def addTags(tags: js.Dictionary[String]): Unit = js.native
    def clearTag(name: String): Unit = js.native

    def request(
        method: String,
        url: Url,
        body: js.UndefOr[js.Any],
        params: js.UndefOr[js.Object]
    ): Response = js.native
    def asyncRequest(
        method: String,
        url: Url,
        body: js.UndefOr[js.Any],
        params: js.UndefOr[js.Object]
    ): js.Promise[Response] = js.native

    def get(url: Url, body: js.UndefOr[js.Any], params: js.UndefOr[js.Object]): Response = js.native
    def post(url: Url, body: js.UndefOr[js.Any], params: js.UndefOr[js.Object]): Response = js.native
    def put(url: Url, body: js.UndefOr[js.Any], params: js.UndefOr[js.Object]): Response = js.native
    def patch(url: Url, body: js.UndefOr[js.Any], params: js.UndefOr[js.Object]): Response = js.native
    def delete(url: Url, body: js.UndefOr[js.Any], params: js.UndefOr[js.Object]): Response = js.native
    def options(url: Url, body: js.UndefOr[js.Any], params: js.UndefOr[js.Object]): Response = js.native
    def head(url: Url, body: js.UndefOr[js.Any], params: js.UndefOr[js.Object]): Response = js.native
    def trace(url: Url, body: js.UndefOr[js.Any], params: js.UndefOr[js.Object]): Response = js.native

    def batch(
        requests: js.Array[js.Any],
        sharedParams: js.UndefOr[js.Object]
    ): js.Array[Response] = js.native
  }
}
