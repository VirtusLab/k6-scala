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
 * Metadata for a gRPC stream message, passed as the second argument to the "data" event handler
 * when using [[Stream.on]] with a two-argument callback.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-net-grpc/stream/message-metadata/ Metadata]]
 */
@js.native
trait StreamMetadata extends js.Object {

  /**
   * Timestamp of the original event (e.g. when the message was received), in k6's internal
   * units.
   */
  def ts: Double = js.native
}
