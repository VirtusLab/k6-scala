package com.github.wiacekm.scalajs.k6.http

import scala.scalajs.js

@js.native
trait OCSP extends js.Object {
  def produced_at: Int = js.native
  def this_update: Int = js.native
  def next_update: Int = js.native
  def revocation_reason: String = js.native
  def revoked_at: Int = js.native
  def status: String = js.native
}
