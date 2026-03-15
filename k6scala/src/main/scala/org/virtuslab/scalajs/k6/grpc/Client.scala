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

/**
 * Native gRPC Client instance (k6/net/grpc). Created via the namespace constructor; use the Scala
 * [[Client]] wrapper and [[org.virtuslab.scalajs.k6.grpc.package#Client Client()]] from the
 * package object for idiomatic usage.
 *
 * Must call [[load]] (during init phase) and [[connect]] before [[invoke]] or [[asyncInvoke]].
 * Invoking before connect throws a k6 runtime error.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-net-grpc/client/ k6 gRPC Client]]
 */
@js.native
trait GrpcClientNative extends js.Object {

  /**
   * Connects to the gRPC server. Blocks until connected or a connection error is thrown. Cannot
   * be called during init phase.
   */
  def connect(address: String, params: js.UndefOr[GrpcConnectParams] = js.undefined): Unit =
    js.native

  /**
   * Makes a unary RPC. The method must have been loaded via [[load]]; [[connect]] must have
   * been called first. Returns a [[GrpcResponse]].
   */
  def invoke(
      url: String,
      request: js.Any,
      params: js.UndefOr[GrpcInvokeParams] = js.undefined
  ): GrpcResponse =
    js.native

  /**
   * Asynchronously makes a unary RPC. Returns a Promise that resolves to [[GrpcResponse]].
   */
  def asyncInvoke(
      url: String,
      request: js.Any,
      params: js.UndefOr[GrpcInvokeParams] = js.undefined
  ): js.Promise[GrpcResponse] =
    js.native

  /** Closes the connection to the gRPC service. */
  def close(): Unit = js.native

  /**
   * Loads and parses protocol buffer definitions. Must be called during the init phase. Use
   * `None` or `Some(Nil)` for default import paths; pass one or more proto file paths.
   */
  def load(importPaths: js.Array[String], protoFiles: js.Array[String]): Unit = js.native
}

/**
 * Scala wrapper for the k6/net/grpc Client. Create with [[Client.apply]](); then call
 * [[load]] (during init), [[connect]] in VU code, [[invoke]] or [[asyncInvoke]] for RPCs, and
 * [[close]] when done.
 *
 * Edge case: calling [[invoke]] before [[connect]] causes a k6 runtime error. Connecting to an
 * unreachable host will eventually timeout according to connect params.
 */
final class Client private (private val inner: GrpcClientNative) {

  /** Native client instance; used by [[Stream]] for construction. */
  private[grpc] def asNative: GrpcClientNative = inner

  /**
   * Loads and parses protocol buffer definitions. Must be called during the init phase. Use
   * `None` or `Some(Nil)` for default import paths; pass one or more proto file paths.
   *
   * @param importPaths
   *   Optional list of paths for proto imports; `None` or `Some(Nil)` for current directory.
   * @param protoFiles
   *   One or more proto file paths (e.g. `"service.proto"`).
   */
  def load(importPaths: Option[Seq[String]], protoFiles: String*): Unit = {
    if (protoFiles.isEmpty)
      throw new IllegalArgumentException("at least one proto file required")
    // k6 expects first arg as []string (array of strings). Use empty array for no paths; Go bridge fails on null/undefined.
    val pathsJs: js.Array[String] = importPaths match {
      case None => new js.Array[String]()
      case Some(s) => s.toJSArray
    }
    inner.load(pathsJs, protoFiles.toJSArray)
  }

  /** Connects to the gRPC server at the given address. Blocks until connected or error. */
  def connect(address: String, params: Option[GrpcConnectParams] = None): Unit =
    inner.connect(address, params.orUndefined)

  /**
   * Invokes a unary RPC. URL format: `package.Service/Method` (leading slash optional). Request
   * must follow Protobuf JSON mapping.
   */
  def invoke(
      url: String,
      request: js.Any,
      params: Option[GrpcInvokeParams] = None
  ): GrpcResponse =
    inner.invoke(url, request, params.orUndefined)

  /** Asynchronously invokes a unary RPC. Returns a Promise of [[GrpcResponse]]. */
  def asyncInvoke(
      url: String,
      request: js.Any,
      params: Option[GrpcInvokeParams] = None
  ): js.Promise[GrpcResponse] =
    inner.asyncInvoke(url, request, params.orUndefined)

  /** Closes the connection to the gRPC service. */
  def close(): Unit =
    inner.close()
}

object Client {

  /** Creates a new gRPC client. Call [[Client.load]] during init, then [[Client.connect]] in VU code. */
  def apply(): Client =
    new Client(
      js.Dynamic
        .newInstance(GrpcNative.asInstanceOf[js.Dynamic].Client)()
        .asInstanceOf[GrpcClientNative]
    )
}
