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

package org.virtuslab.scalajs.k6

package object grpc {

  /** Alias for [[StatusConstants]] status codes; e.g. use `grpc.StatusOK` like in k6 JS. For streaming use [[Stream]].apply(client, url, params). */
  val StatusOK: Int = StatusConstants.OK
  val StatusCanceled: Int = StatusConstants.Canceled
  val StatusUnknown: Int = StatusConstants.Unknown
  val StatusInvalidArgument: Int = StatusConstants.InvalidArgument
  val StatusDeadlineExceeded: Int = StatusConstants.DeadlineExceeded
  val StatusNotFound: Int = StatusConstants.NotFound
  val StatusAlreadyExists: Int = StatusConstants.AlreadyExists
  val StatusPermissionDenied: Int = StatusConstants.PermissionDenied
  val StatusResourceExhausted: Int = StatusConstants.ResourceExhausted
  val StatusFailedPrecondition: Int = StatusConstants.FailedPrecondition
  val StatusAborted: Int = StatusConstants.Aborted
  val StatusOutOfRange: Int = StatusConstants.OutOfRange
  val StatusUnimplemented: Int = StatusConstants.Unimplemented
  val StatusInternal: Int = StatusConstants.Internal
  val StatusUnavailable: Int = StatusConstants.Unavailable
  val StatusDataLoss: Int = StatusConstants.DataLoss
  val StatusUnauthenticated: Int = StatusConstants.Unauthenticated
}
