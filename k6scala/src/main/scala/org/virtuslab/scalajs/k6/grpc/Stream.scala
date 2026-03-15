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
 * Native gRPC Stream instance (k6/net/grpc). Created with `new Stream(client, url, params?)`.
 * Use the Scala [[Stream]] wrapper and [[org.virtuslab.scalajs.k6.grpc.package#Stream Stream()]]
 * from the package object. The client must be connected before creating a stream.
 *
 * Events: "data" (server sent a message), "error" (error occurred, stream closed), "end" (server
 * closed the incoming stream). Multiple handlers for the same event all fire.
 *
 * Edge case: calling [[write]] after [[end]] is undefined in k6 — avoid doing so.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-net-grpc/stream/ k6 gRPC Stream]]
 */
@js.native
trait GrpcStreamNative extends js.Object {

  /**
   * Registers a handler for a stream event. Events: "data", "error", "end". Prefer typed
   * wrappers (onData, onError, onEnd) from the Scala [[Stream]] class.
   */
  def on(event: String, handler: js.Function): Unit = js.native

  /**
   * Writes a message to the stream. The message must follow Protobuf JSON mapping. After
   * finishing sends, call [[end]] to signal completion.
   */
  def write(message: js.Any): Unit = js.native

  /** Signals to the server that the client has finished sending. */
  def end(): Unit = js.native
}

/**
 * Scala wrapper for the k6/net/grpc Stream. Create with [[Stream.apply]](client, url, params).
 * Register handlers with [[onData]], [[onDataWithMetadata]], [[onError]], [[onEnd]], then
 * [[write]] messages and [[end]] when done.
 *
 * Supports client, server, and bidirectional streaming. The client must be connected ([[Client.connect]]
 * called) before creating a stream.
 *
 * Edge case: multiple handlers for the same event all fire. Write after end — avoid; behavior
 * is undefined in k6.
 */
final class Stream private (private val inner: GrpcStreamNative) {

  /**
   * Registers a handler for the "data" event (server sent a message). The handler receives the
   * deserialized message (Protobuf JSON mapping). Multiple data handlers all fire.
   */
  def onData(handler: js.Any => Unit): Unit =
    inner.on(StreamEvent.Data.name, js.Any.fromFunction1(handler))

  /**
   * Registers a handler for the "data" event that also receives message metadata (e.g.
   * timestamp). Multiple handlers all fire.
   */
  def onDataWithMetadata(handler: (js.Any, StreamMetadata) => Unit): Unit =
    inner.on(StreamEvent.Data.name, js.Any.fromFunction2(handler))

  /**
   * Registers a handler for the "error" event. The stream is closed when an error occurs.
   * Handler receives the error object.
   */
  def onError(handler: js.Object => Unit): Unit =
    inner.on(StreamEvent.Error.name, js.Any.fromFunction1(handler))

  /** Registers a handler for the "end" event (server closed the incoming stream). */
  def onEnd(handler: () => Unit): Unit =
    inner.on(StreamEvent.End.name, js.Any.fromFunction0(handler))

  /**
   * Raw event registration. Prefer [[onData]], [[onError]], [[onEnd]] for type safety.
   */
  def on(event: String, handler: js.Function): Unit =
    inner.on(event, handler)

  /** Writes a message to the stream. Message must follow Protobuf JSON mapping. */
  def write(message: js.Any): Unit =
    inner.write(message)

  /** Signals to the server that the client has finished sending. */
  def end(): Unit =
    inner.end()
}

object Stream {

  /**
   * Creates a gRPC stream for the given method. The client must be connected. URL format:
   * `package.Service/Method`. Optional params (metadata, tags, timeout) apply to the stream.
   *
   * @param client
   *   Connected gRPC client ([[Client.connect]] must have been called).
   * @param url
   *   Method URL (e.g. `"main.RouteGuide/RecordRoute"`).
   * @param params
   *   Optional stream/invoke params; `None` for defaults.
   */
  def apply(
      client: Client,
      url: String,
      params: Option[GrpcInvokeParams] = None
  ): Stream = {
    val nativeClient = client.asNative
    val streamNative = js.Dynamic
      .newInstance(GrpcNative.asInstanceOf[js.Dynamic].Stream)(
        nativeClient,
        url,
        params.orUndefined
      )
      .asInstanceOf[GrpcStreamNative]
    new Stream(streamNative)
  }
}
