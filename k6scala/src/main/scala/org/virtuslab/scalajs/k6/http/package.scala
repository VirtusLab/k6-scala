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
import scala.scalajs.js.|
import scala.scalajs.js.typedarray._
import js.JSConverters._
import org.virtuslab.scalajs.converters.FromJSPromise
import org.virtuslab.scalajs.k6.http.AsyncRequest
import org.virtuslab.scalajs.k6.http.Batch._

package object http {
  type Body = String | js.Object | ArrayBuffer
  type BodyOpt = js.UndefOr[Body]
  type Data = String | js.Array[Int] | ArrayBuffer
  type ParamsOpt = js.UndefOr[Params]
  type URL = String | HttpURL
  type ExpectedStatusesCallback = js.Array[Int]
  type CipherSuiteType = String

  def formData(fields: Map[String, String]): js.Object =
    fields.toJSDictionary.asInstanceOf[js.Object]

  def asyncRequest[F[_]: FromJSPromise](
      method: HttpMethod,
      url: URL,
      body: Option[Body] = None,
      params: Option[Params] = None
  ): F[Response] = AsyncRequest.asyncRequest(method, url, body, params)
  def request(
      method: HttpMethod,
      url: URL,
      body: Option[Body] = None,
      params: Option[Params] = None
  ): Response = Http.request(method.toJSType, url, body.orUndefined, params.orUndefined)
  def batch(requests: Seq[BatchRequest]): Seq[Response] =
    Http
      .batch(requests.toJSArray.asInstanceOf[BatchRequests])
      .asInstanceOf[js.Array[Response]]
      .toSeq
  def batch(requests: Map[String, BatchRequest]): Map[String, Response] =
    Http
      .batch(requests.toJSDictionary.asInstanceOf[BatchRequests])
      .asInstanceOf[js.Dictionary[Response]]
      .toMap
  def cookieJar(): CookieJar =
    Http.cookieJar()
  def del(url: URL, body: Option[Body] = None, params: Option[Params] = None): Response =
    Http.del(url, body.orUndefined, params.orUndefined)
  def file(
      data: String,
      filename: Option[String] = None,
      contentType: Option[String] = None
  ): FileData =
    Http.file(data, filename.orUndefined, contentType.orUndefined)
  def get(url: URL, params: Option[Params] = None): Response =
    Http.get(url, params.orUndefined)
  def head(url: URL, params: Option[Params] = None): Response =
    Http.head(url, params.orUndefined)
  def options(url: URL, body: Option[Body] = None, params: Option[Params] = None): Response =
    Http.options(url, body.orUndefined, params.orUndefined)
  def patch(url: URL, body: Option[Body] = None, params: Option[Params] = None): Response =
    Http.patch(url, body.orUndefined, params.orUndefined)
  def post(url: URL, body: Option[Body] = None, params: Option[Params] = None): Response =
    Http.post(url, body.orUndefined, params.orUndefined)
  def put(url: URL, body: Option[Body] = None, params: Option[Params] = None): Response =
    Http.put(url, body.orUndefined, params.orUndefined)
}
