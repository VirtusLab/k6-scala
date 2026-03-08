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

package object html {

  /**
   * Parse an HTML string and return a [[Selection]] wrapping the document.
   *
   * Equivalent to the `parseHTML` function from the `k6/html` module.
   *
   * @param src
   *   raw HTML string to parse
   * @return
   *   a [[Selection]] representing the parsed document
   * @see
   *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-html/parsehtml/ k6 parseHTML]]
   */
  def parseHTML(src: String): Selection = Html.parseHTML(src)
}
