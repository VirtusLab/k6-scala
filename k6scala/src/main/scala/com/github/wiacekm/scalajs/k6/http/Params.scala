package com.github.wiacekm.scalajs.k6.http

import scala.scalajs.js
import scala.scalajs.js.|
import scala.concurrent.duration.FiniteDuration
import com.github.wiacekm.scalajs.k6.utils.DurationConverters._
import scala.scalajs.js.JSConverters._

@js.native
trait Params extends js.Object {
  def auth: js.UndefOr[AuthMethod] = js.native
  def compression: js.UndefOr[String] = js.native
  def cookies: js.UndefOr[js.Dictionary[String | ParamsCookieValue]] =
    js.native // TODO: add Cookie type
  def headers: js.UndefOr[js.Dictionary[String]] = js.native
  def jar: js.UndefOr[CookieJar] = js.native
  def redirects: js.UndefOr[Int] = js.native
  def responseType: js.UndefOr[ResponseType] = js.native
  def tags: js.UndefOr[js.Dictionary[String]] = js.native
  def timeout: js.UndefOr[String | Int] = js.native // TODO: scala Duration
  def responseCallback: js.UndefOr[ExpectedStatusesCallback] = js.native
}

object Params {
  def apply(
      auth: Option[String] = None,
      compression: Option[String] = None,
      cookies: Option[Map[String, String]] = None,
      headers: Option[Map[String, String]] = None,
      jar: Option[CookieJar] = None,
      redirects: Option[Int] = None,
      responseType: Option[ResponseType],
      tags: Option[Map[String, String]],
      timeout: Option[FiniteDuration],
      responseCallback: Option[ExpectedStatusesCallback]
  ): Params =
    js.Dynamic
      .literal(
        auth = auth.orUndefined,
        compression = compression.orUndefined,
        cookies = cookies.map(_.toJSDictionary).orUndefined,
        headers = headers.map(_.toJSDictionary).orUndefined,
        jar = jar.orUndefined,
        redirects = redirects.orUndefined,
        responseType = responseType.map(_.asString).orUndefined,
        tags = tags.map(_.toJSDictionary).orUndefined,
        timeout = timeout.map(_.toK6).orUndefined,
        responseCallback = responseCallback.orUndefined
      )
      .asInstanceOf[Params]
}

sealed trait AuthMethod {

  def asString: String
}

object AuthMethod {
  // TODO: rewrite toString
  case object Basic extends AuthMethod {
    override def asString: String = "basic"
  }
  case object Digest extends AuthMethod {
    override def asString: String = "digest"
  }
  case object Ntlm extends AuthMethod {
    override def asString: String = "ntlm"
  }
}

sealed trait ResponseType {
  def asString: String
}

object ResponseType {
  // TODO: rewrite toString
  case object Binary extends ResponseType {
    override def asString: String = "binary"
  }
  case object None extends ResponseType {
    override def asString: String = "none"
  }
  case object Text extends ResponseType {
    override def asString: String = "text"
  }

}
@js.native
trait ParamsCookieValue extends js.Object {
  val value: js.UndefOr[String] = js.native
  val replace: js.UndefOr[Boolean] = js.native
}

object ParamsCookieValue {

  def apply(
      value: Option[String] = None,
      replace: Option[Boolean] = None
  ): ParamsCookieValue =
    js.Dynamic
      .literal(
        value = value.orUndefined,
        replace = replace.orUndefined
      )
      .asInstanceOf[ParamsCookieValue]
}
