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

package org.virtuslab.scalajs.k6.html

import scala.scalajs.js

/**
 * Minimal jQuery-like Selection facade for the k6/html module.
 *
 * A `Selection` represents a set of HTML elements returned by [[parseHTML]] or
 * [[org.virtuslab.scalajs.k6.http.Response.html Response.html()]]. It supports CSS-selector
 * traversal, content extraction, and sibling/parent navigation.
 *
 * This is a minimal facade covering the subset of methods needed by `Response.html()`,
 * `Response.submitForm()`, and `Response.clickLink()`. Full k6/html support is planned for Phase
 * 4.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-html/selection/ k6 Selection API]]
 */
@js.native
trait Selection extends js.Object {

  /** Find descendants matching a CSS selector. */
  def find(selector: String): Selection = js.native

  /** Filter the current set of elements by a CSS selector. */
  def filter(selector: String): Selection = js.native

  /** Get the combined text content of all matched elements. */
  def text(): String = js.native

  /** Get the HTML content of the first matched element. */
  def html(): String = js.native

  /**
   * Get the value of an attribute of the first matched element.
   *
   * Returns `undefined` when the selection is empty or the attribute does not exist. Follows
   * jQuery semantics.
   */
  def attr(name: String): js.UndefOr[String] = js.native

  /**
   * Get the direct children of each matched element.
   *
   * When a selector is provided, only children matching the CSS selector are included.
   *
   * @param selector
   *   optional CSS selector to filter children
   */
  def children(selector: js.UndefOr[String] = js.undefined): Selection = js.native

  /** Get the parent of each matched element. */
  def parent(): Selection = js.native

  /** Get the immediately following sibling of each matched element. */
  def next(): Selection = js.native

  /** Get the immediately preceding sibling of each matched element. */
  def prev(): Selection = js.native

  /** Reduce the set to the first matched element. */
  def first(): Selection = js.native

  /** Reduce the set to the last matched element. */
  def last(): Selection = js.native

  /**
   * Reduce the set to the element at the given index.
   *
   * @param index
   *   zero-based index
   */
  def eq(index: Int): Selection = js.native

  /** Get the number of matched elements. */
  def size(): Int = js.native

  /**
   * Iterate over matched elements, executing a function for each.
   *
   * @param fn
   *   callback receiving `(index, element)` where `element` is a raw JS object
   */
  def each(fn: js.Function2[Int, js.Object, Unit]): Selection = js.native
}
