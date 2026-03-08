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
import scala.scalajs.js.|
import scala.scalajs.js.typedarray.ArrayBuffer
import scala.scalajs.js.JSConverters._
import org.virtuslab.scalajs.k6.http.Batch._

@js.native
trait StructuredRequestBody extends js.Object

object StructuredRequestBody {
  def apply(fields: Map[String, String | FileData]): StructuredRequestBody = {
    val obj = js.Dynamic.literal()
    fields.foreach {
      case (key, value) =>
        obj.updateDynamic(key)(value.asInstanceOf[js.Any])
    }
    obj.asInstanceOf[StructuredRequestBody]
  }
}

@js.native
trait ObjectBatchRequest extends js.Object {
  val method: String = js.native
  val url: URL = js.native
  val body: js.UndefOr[RequestBody] = js.native
  val params: js.UndefOr[Params] = js.native
}

object ObjectBatchRequest {
  def apply(
      method: String,
      url: URL,
      body: Option[RequestBody] = None,
      params: Option[Params] = None
  ): ObjectBatchRequest =
    js.Dynamic
      .literal(
        method = method,
        url = url.asInstanceOf[js.Any],
        body = body.orUndefined.asInstanceOf[js.Any],
        params = params.orUndefined
      )
      .asInstanceOf[ObjectBatchRequest]
}

object Batch {
  type RequestBody = String | StructuredRequestBody | ArrayBuffer
  type ArrayBatchRequest = js.Tuple4[String, URL, js.UndefOr[RequestBody], js.UndefOr[Params]]
  type BatchRequest = String | HttpURL | ArrayBatchRequest | ObjectBatchRequest

  type BatchRequests = js.Array[BatchRequest] | js.Dictionary[BatchRequest]

  type BatchResponses = js.Array[Response] | js.Dictionary[Response]
}
