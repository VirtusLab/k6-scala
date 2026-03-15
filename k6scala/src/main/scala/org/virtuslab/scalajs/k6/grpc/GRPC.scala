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
import scala.scalajs.js.annotation.JSImport

/**
 * Native k6/net/grpc namespace. Provides the [[Client]] constructor. Use [[Client]]() to create
 * a gRPC client; use [[StatusConstants]] for status code comparison.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-net-grpc/ k6 net/grpc]]
 */
@js.native
@JSImport("k6/net/grpc", JSImport.Namespace)
private[grpc] object GrpcNative extends js.Object {

  /** Constructor for the gRPC Client. Use [[Client]]() from the package object in Scala code. */
  val Client: js.Dynamic = js.native
}
