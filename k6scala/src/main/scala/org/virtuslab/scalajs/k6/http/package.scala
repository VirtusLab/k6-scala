package org.virtuslab.scalajs.k6

import scala.scalajs.js
import scala.scalajs.js.|
import js.JSConverters._
import scala.scalajs.js.typedarray._
import org.virtuslab.scalajs.converters.FromJSPromise
import org.virtuslab.scalajs.k6.http.AsyncRequest
import org.virtuslab.scalajs.k6.http.Batch._

package object http {
  type BodyOpt = js.UndefOr[String | js.Object | ArrayBuffer]
  type Data = String | js.Array[Int] | ArrayBuffer
  type ParamsOpt = js.UndefOr[Params]
  type URL = String | HttpURL
  type ExpectedStatusesCallback = js.Array[Int]
  type CipherSuiteType = String

  def asyncRequest[F[_]: FromJSPromise](
      method: HttpMethod,
      url: URL,
      body: Option[String] = None, // TODO add other formats
      params: Option[Params] = None
  ): F[Response] = AsyncRequest.asyncRequest(method, url, body, params)
  def request(
      method: HttpMethod,
      url: URL,
      body: Option[String] = None, // TODO add other formats
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
  def del(url: URL, body: Option[String] = None, params: Option[Params] = None): Response =
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
  def options(url: URL, body: Option[String] = None, params: Option[Params] = None): Response =
    Http.options(url, body.orUndefined, params.orUndefined)
  def patch(url: URL, body: Option[String] = None, params: Option[Params] = None): Response =
    Http.patch(url, body.orUndefined, params.orUndefined)
  def post(url: URL, body: Option[String] = None, params: Option[Params] = None): Response =
    Http.post(url, body.orUndefined, params.orUndefined)
  def put(url: URL, body: Option[String] = None, params: Option[Params] = None): Response =
    Http.put(url, body.orUndefined, params.orUndefined)
}
