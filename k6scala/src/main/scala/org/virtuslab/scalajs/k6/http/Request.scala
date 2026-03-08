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

import scala.scalajs.{js => js}

@js.native
trait Request extends js.Object {
  def body: String = js.native
  def cookies: Any = js.native
  def error: String = js.native
  def error_code: Int = js.native
  def headers: js.Dictionary[String] = js.native
  def status: Int = js.native
  def status_text: String = js.native
  def url: String = js.native
  def json(selector: String): js.Object = js.native
}
