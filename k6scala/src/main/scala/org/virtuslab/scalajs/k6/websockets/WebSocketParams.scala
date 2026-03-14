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

package org.virtuslab.scalajs.k6.websockets

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import org.virtuslab.scalajs.k6.http.CookieJar

/**
 * Parameters for the k6/websockets WebSocket constructor. Options for the initial HTTP request
 * used to establish the WebSocket connection.
 *
 * @see
 *   [[https://k6.io/docs/javascript-api/k6-websockets/params/ k6 websockets Params]]
 */
@js.native
trait WebSocketParams extends js.Object {

  /** Custom HTTP headers for the WebSocket handshake request. */
  def headers: js.UndefOr[js.Dictionary[String]] = js.native

  /** Custom metric tags for filtering and thresholds on WebSocket metrics. */
  def tags: js.UndefOr[js.Dictionary[String]] = js.native

  /**
   * Compression algorithm. The only supported value is `"deflate"`. Unset or empty means no
   * compression.
   */
  def compression: js.UndefOr[String] = js.native

  /**
   * Cookie jar for the initial HTTP request. If empty, the default VU cookie jar is used.
   */
  def jar: js.UndefOr[CookieJar] = js.native
}

object WebSocketParams {

  def apply(
      headers: Option[Map[String, String]] = None,
      tags: Option[Map[String, String]] = None,
      compression: Option[String] = None,
      jar: Option[CookieJar] = None
  ): WebSocketParams =
    js.Dynamic
      .literal(
        headers = headers.map(_.toJSDictionary).orUndefined,
        tags = tags.map(_.toJSDictionary).orUndefined,
        compression = compression.orUndefined,
        jar = jar.orUndefined
      )
      .asInstanceOf[WebSocketParams]
}
