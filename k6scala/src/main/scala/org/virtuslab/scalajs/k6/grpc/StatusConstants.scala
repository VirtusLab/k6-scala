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

/**
 * gRPC status code constants. Use these to compare with [[GrpcResponse.status]] (e.g.
 * `response.status == StatusConstants.OK`). Matches the k6/net/grpc and standard gRPC status
 * codes.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-net-grpc/constants/ k6 gRPC Constants]]
 */
object StatusConstants {

  /** OK — returned on success. */
  val OK: Int = 0

  /** Canceled — operation was canceled (typically by the caller). */
  val Canceled: Int = 1

  /** Unknown — unknown error. */
  val Unknown: Int = 2

  /** InvalidArgument — client specified an invalid argument. */
  val InvalidArgument: Int = 3

  /** DeadlineExceeded — operation expired before completion. */
  val DeadlineExceeded: Int = 4

  /** NotFound — requested entity (e.g. file or directory) was not found. */
  val NotFound: Int = 5

  /** AlreadyExists — attempt to create an entity failed because one already exists. */
  val AlreadyExists: Int = 6

  /** PermissionDenied — caller does not have permission for the operation. */
  val PermissionDenied: Int = 7

  /** ResourceExhausted — some resource has been exhausted (e.g. quota, file system). */
  val ResourceExhausted: Int = 8

  /** FailedPrecondition — system not in state required for the operation. */
  val FailedPrecondition: Int = 9

  /** Aborted — operation aborted (e.g. concurrency, transaction abort). */
  val Aborted: Int = 10

  /** OutOfRange — operation attempted past the valid range. */
  val OutOfRange: Int = 11

  /** Unimplemented — operation not implemented or not supported in this service. */
  val Unimplemented: Int = 12

  /** Internal — internal errors; invariants of the underlying system broken. */
  val Internal: Int = 13

  /** Unavailable — service currently unavailable (transient; retry with backoff may help). */
  val Unavailable: Int = 14

  /** DataLoss — unrecoverable data loss or corruption. */
  val DataLoss: Int = 15

  /** Unauthenticated — request does not have valid authentication credentials. */
  val Unauthenticated: Int = 16
}
