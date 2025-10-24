package org.virtuslab.scalajs.k6

import scala.scalajs.js
import scala.scalajs.js.|
import js.JSConverters._
import scala.scalajs.js.typedarray._
import org.virtuslab.scalajs.converters.FromJSPromise

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
  ): F[Response] =
    summon[FromJSPromise[F]](
      Http.asyncRequest(method.toJSType, url, body.orUndefined, params.orUndefined)
    )
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
