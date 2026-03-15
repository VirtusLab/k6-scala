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

/**
 * gRPC response returned by [[Client.invoke]] and [[Client.asyncInvoke]]. Compare
 * `status` with [[StatusConstants]] (e.g. `response.status == StatusConstants.OK`).
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-net-grpc/response/ k6 gRPC Response]]
 */
@js.native
trait GrpcResponse extends js.Object {

  /**
   * The gRPC status code. Use [[StatusConstants]] for comparison (e.g.
   * `response.status == StatusConstants.OK`).
   */
  def status: Int = js.native

  /**
   * The successful protobuf message serialized to JSON. `null` if `status !== StatusConstants.OK`.
   */
  def message: js.Any = js.native

  /** Key-value metadata headers returned by the gRPC server. */
  def headers: js.Dictionary[js.Any] = js.native

  /** Key-value metadata trailers returned by the gRPC server. */
  def trailers: js.Dictionary[js.Any] = js.native

  /**
   * If `status !== StatusConstants.OK`, the error protobuf message serialized to JSON; otherwise
   * `null`.
   */
  def error: js.Any = js.native
}
