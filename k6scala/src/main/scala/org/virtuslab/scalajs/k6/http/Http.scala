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
import scala.scalajs.js.annotation._
import org.virtuslab.scalajs.k6.http.Batch._

@js.native
@JSImport("k6/http", JSImport.Namespace)
private[http] object Http extends js.Object {
  val url: js.Function2[js.Array[String], js.Array[Any], HttpURL] = js.native
  def asyncRequest(
      method: HttpMethodType,
      url: URL,
      body: BodyOpt,
      params: ParamsOpt
  ): js.Promise[Response] =
    js.native
  def batch(requests: BatchRequests): BatchResponses = js.native
  def cookieJar(): CookieJar = js.native
  def del(url: URL, body: BodyOpt, params: ParamsOpt): Response = js.native
  def file(data: String, filename: js.UndefOr[String], contentType: js.UndefOr[String]): FileData =
    js.native
  def get(url: URL, params: ParamsOpt): Response = js.native
  def head(url: URL, params: ParamsOpt): Response = js.native
  def options(url: URL, body: BodyOpt, params: ParamsOpt): Response = js.native
  def patch(url: URL, body: BodyOpt, params: ParamsOpt): Response = js.native
  def post(url: URL, body: BodyOpt, params: ParamsOpt): Response = js.native
  def put(url: URL, body: BodyOpt, params: ParamsOpt): Response = js.native
  def request(
      method: HttpMethodType,
      url: URL,
      body: BodyOpt,
      params: ParamsOpt
  ): Response =
    js.native
}

trait HttpMethodType extends js.Any

sealed trait HttpMethod {
  def toJSType: HttpMethodType
}
object HttpMethod {
  case object GET extends HttpMethod {
    def toJSType: HttpMethodType = "GET".asInstanceOf[HttpMethodType]
  }
  case object PUT extends HttpMethod {
    def toJSType: HttpMethodType = "PUT".asInstanceOf[HttpMethodType]
  }
  case object DELETE extends HttpMethod {
    def toJSType: HttpMethodType = "DELETE".asInstanceOf[HttpMethodType]
  }
  case object POST extends HttpMethod {
    def toJSType: HttpMethodType = "POST".asInstanceOf[HttpMethodType]
  }
  case object HEAD extends HttpMethod {
    def toJSType: HttpMethodType = "HEAD".asInstanceOf[HttpMethodType]
  }
  case object OPTIONS extends HttpMethod {
    def toJSType: HttpMethodType = "OPTIONS".asInstanceOf[HttpMethodType]
  }
  case object PATCH extends HttpMethod {
    def toJSType: HttpMethodType = "PATCH".asInstanceOf[HttpMethodType]
  }
  case object CONNECT extends HttpMethod {
    def toJSType: HttpMethodType = "CONNECT".asInstanceOf[HttpMethodType]
  }
  case object TRACE extends HttpMethod {
    def toJSType: HttpMethodType = "TRACE".asInstanceOf[HttpMethodType]
  }
}
