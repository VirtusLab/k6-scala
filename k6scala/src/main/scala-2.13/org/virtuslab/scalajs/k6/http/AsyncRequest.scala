package org.virtuslab.scalajs.k6.http

import scala.scalajs.js
import scala.scalajs.js.|
import js.JSConverters._
import scala.scalajs.js.typedarray._
import org.virtuslab.scalajs.converters.FromJSPromise

object AsyncRequest {

  def asyncRequest[F[_]: FromJSPromise](
      method: HttpMethod,
      url: URL,
      body: Option[String] = None, // TODO add other formats
      params: Option[Params] = None
  ): F[Response] =
    implicitly[FromJSPromise[F]].apply(
      Http.asyncRequest(method.toJSType, url, body.orUndefined, params.orUndefined)
    )
}
