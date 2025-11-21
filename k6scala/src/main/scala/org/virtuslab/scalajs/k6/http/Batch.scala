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