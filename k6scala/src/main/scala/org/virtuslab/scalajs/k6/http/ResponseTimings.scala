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

package org.virtuslab.scalajs.k6.http

import scala.scalajs.js

/**
 * Timing breakdown for an HTTP response.
 *
 * All values are in milliseconds. Returned by `Response.timings`.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-http/response/ k6 Response API]]
 */
@js.native
trait ResponseTimings extends js.Object {

  /**
   * Time spent blocked before initiating the request (e.g. waiting for a free TCP connection
   * slot). Milliseconds.
   */
  def blocked: Double = js.native

  /** Time spent establishing a TCP connection to the remote host. Milliseconds. */
  def connecting: Double = js.native

  /** Time spent performing the TLS handshake with the remote host. Milliseconds. */
  def tls_handshaking: Double = js.native

  /** Time spent sending data to the remote host. Milliseconds. */
  def sending: Double = js.native

  /**
   * Time spent waiting for a response from the remote host (a.k.a. "time to first byte", or
   * TTFB). Milliseconds.
   */
  def waiting: Double = js.native

  /** Time spent receiving data from the remote host. Milliseconds. */
  def receiving: Double = js.native

  /** Total time for the request. Equal to `sending + waiting + receiving`. Milliseconds. */
  def duration: Double = js.native
}
