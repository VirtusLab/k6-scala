package com.github.wiacekm.scalajs.k6.http

import scala.scalajs.js

@js.native
trait Response extends js.Object {
  def body: String = js.native
  def cookies: js.Dictionary[ResponseCookie] = js.native
  def error_code: Int = js.native
  def headers: js.Dictionary[String] = js.native
  def ocsp: OCSP = js.native
  def proto: ProtocolType
  def remote_ip: String
  def remote_port: Int
  def request: ResponseRequest = js.native
  def status: Int = js.native
  def status_text: String = js.native
  def tls_cipher_suite: CipherSuiteType = js.native
  def tls_version: String = js.native
  def url: String = js.native
}
