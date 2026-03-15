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

package org.virtuslab.scalajs.k6.grpc

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.concurrent.duration.FiniteDuration
import org.virtuslab.scalajs.k6.utils.DurationConverters._

/**
 * Connection parameters for [[Client.connect]]. TLS defaults from k6 options are used when `tls`
 * is not set.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-net-grpc/client/client-connect/ ConnectParams]]
 */
@js.native
trait GrpcConnectParams extends js.Object {

  /** If `true`, connect using plaintext (insecure). Defaults to `false` (TLS). */
  def plaintext: js.UndefOr[Boolean] = js.native

  /** Whether to use the gRPC server reflection protocol when connecting. */
  def reflect: js.UndefOr[Boolean] = js.native

  /** Custom metadata for the reflection request. */
  def reflectMetadata: js.UndefOr[js.Dictionary[js.Any]] = js.native

  /** Connection timeout (string duration or number of milliseconds). */
  def timeout: js.UndefOr[js.Any] = js.native

  /** Maximum message size in bytes the client can receive (default ~4MB). */
  def maxReceiveSize: js.UndefOr[Double] = js.native

  /** Maximum message size in bytes the client can send (default ~2GB). */
  def maxSendSize: js.UndefOr[Double] = js.native

  /** TLS settings for the connection. */
  def tls: js.UndefOr[GrpcTlsParams] = js.native
}

/**
 * TLS settings for gRPC connection. If not defined, main TLS config from k6 options is used.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-net-grpc/client/client-connect/ ConnectParams TLS]]
 */
@js.native
trait GrpcTlsParams extends js.Object {

  /** PEM formatted client certificate. */
  def cert: js.UndefOr[String] = js.native

  /** PEM formatted client private key. */
  def key: js.UndefOr[String] = js.native

  /** Password for decrypting the client's private key. */
  def password: js.UndefOr[String] = js.native

  /** PEM formatted CA certificate(s); string or array of strings. */
  def cacerts: js.UndefOr[js.Any] = js.native
}

/**
 * Request-specific parameters for [[Client.invoke]] and [[Client.asyncInvoke]] (metadata, tags,
 * timeout). Keys ending with `-bin` in metadata are treated as binary by gRPC.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-net-grpc/params/ k6 gRPC Params]]
 */
@js.native
trait GrpcInvokeParams extends js.Object {

  /**
   * Value of the :authority pseudo-header and server name for auth handshake. If missing,
   * derived from hostname.
   */
  def authority: js.UndefOr[String] = js.native

  /**
   * If true, response messages are discarded (reduces memory and GC; can improve test
   * reliability). Default `false`.
   */
  def discardResponseMessage: js.UndefOr[Boolean] = js.native

  /** Custom metadata key-value pairs to add to the request. */
  def metadata: js.UndefOr[js.Dictionary[js.Any]] = js.native

  /** Metric tags for this request (for filtering response time metrics). */
  def tags: js.UndefOr[js.Dictionary[String]] = js.native

  /** Request timeout. Default `"60s"`. String duration or number of milliseconds. */
  def timeout: js.UndefOr[js.Any] = js.native
}

object GrpcConnectParams {

  def apply(
      plaintext: Option[Boolean] = None,
      reflect: Option[Boolean] = None,
      reflectMetadata: Option[Map[String, js.Any]] = None,
      timeout: Option[FiniteDuration] = None,
      maxReceiveSize: Option[Double] = None,
      maxSendSize: Option[Double] = None,
      tls: Option[GrpcTlsParams] = None
  ): GrpcConnectParams =
    js.Dynamic
      .literal(
        plaintext = plaintext.orUndefined,
        reflect = reflect.orUndefined,
        reflectMetadata = reflectMetadata
          .map(m => m.foldLeft(js.Dictionary.empty[js.Any])((d, p) => { d(p._1) = p._2; d }))
          .orUndefined,
        timeout = timeout.map(_.toK6).orUndefined,
        maxReceiveSize = maxReceiveSize.orUndefined,
        maxSendSize = maxSendSize.orUndefined,
        tls = tls.orUndefined
      )
      .asInstanceOf[GrpcConnectParams]
}

object GrpcTlsParams {

  def apply(
      cert: Option[String] = None,
      key: Option[String] = None,
      password: Option[String] = None,
      cacerts: Option[Either[String, Seq[String]]] = None
  ): GrpcTlsParams = {
    val cacertsJs = cacerts match {
      case None => js.undefined.asInstanceOf[js.Any]
      case Some(Left(s)) => s.asInstanceOf[js.Any]
      case Some(Right(seq)) => seq.toJSArray.asInstanceOf[js.Any]
    }
    js.Dynamic
      .literal(
        cert = cert.orUndefined,
        key = key.orUndefined,
        password = password.orUndefined,
        cacerts = cacertsJs
      )
      .asInstanceOf[GrpcTlsParams]
  }
}

object GrpcInvokeParams {

  def apply(
      authority: Option[String] = None,
      discardResponseMessage: Option[Boolean] = None,
      metadata: Option[Map[String, js.Any]] = None,
      tags: Option[Map[String, String]] = None,
      timeout: Option[FiniteDuration] = None
  ): GrpcInvokeParams =
    js.Dynamic
      .literal(
        authority = authority.orUndefined,
        discardResponseMessage = discardResponseMessage.orUndefined,
        metadata = metadata
          .map(m => m.foldLeft(js.Dictionary.empty[js.Any])((d, p) => { d(p._1) = p._2; d }))
          .orUndefined,
        tags = tags.map(_.toJSDictionary).orUndefined,
        timeout = timeout.map(_.toK6).orUndefined
      )
      .asInstanceOf[GrpcInvokeParams]
}
