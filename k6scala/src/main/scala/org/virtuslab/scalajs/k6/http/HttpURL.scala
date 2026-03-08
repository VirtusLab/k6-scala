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
import scala.scalajs.js.JSConverters._

@js.native
trait HttpURL extends js.Object {
  val href: String
}

object HttpURL {
  implicit class UrlInterpolator(val sc: StringContext) extends AnyVal {
    def url(args: Any*): HttpURL = {
      Http.url(sc.parts.toJSArray, args.toJSArray)
    }
  }
}
