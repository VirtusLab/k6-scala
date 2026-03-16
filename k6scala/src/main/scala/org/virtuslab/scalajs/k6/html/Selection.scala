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
 * jQuery-like Selection facade for the k6/html module.
 *
 * A `Selection` represents a set of HTML nodes returned by [[parseHTML]] or
 * [[org.virtuslab.scalajs.k6.http.Response.html Response.html()]]. It supports CSS selector
 * traversal, filtering, content extraction, serialization, and DOM-style navigation.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-html/selection/ k6 Selection API]]
 */
@js.native
trait Selection extends js.Object {

  /** Get the value of an attribute of the first matched element. */
  def attr(name: String): js.UndefOr[String] = js.native

  /**
   * Get the children of each element in the set of matched elements, optionally filtered by a
   * selector.
   */
  def children(selector: js.UndefOr[String] = js.undefined): Selection = js.native

  /**
   * Get the first element that matches the selector by testing the element itself and traversing
   * up through its ancestors.
   */
  def closest(selector: String): Selection = js.native

  /**
   * Get the children of each element in the set of matched elements, including text and comment
   * nodes.
   */
  def contents(): Selection = js.native

  /**
   * Return the value at the named data store for the first element in the set of matched elements.
   *
   * When `key` is omitted, returns the full data object.
   */
  def data(key: js.UndefOr[String] = js.undefined): js.Any = js.native

  /**
   * Iterate and execute a function for each matched element.
   *
   * @param fn
   *   callback receiving `(index, element)`
   */
  def each(fn: js.Function2[Int, Element, Unit]): Selection = js.native

  /** Reduce the set of matched elements to the one at the specified index. */
  def eq(index: Int): Selection = js.native

  /** Reduce the set of matched elements to those that match the selector. */
  def filter(selector: String): Selection = js.native

  /** Find descendants filtered by a selector. */
  def find(selector: String): Selection = js.native

  /** Reduce the set of matched elements to the first in the set. */
  def first(): Selection = js.native

  /** Retrieve the [[Element]] matched by the selector at the given index. */
  def get(index: Int): Element = js.native

  /** Reduce the set of matched elements to those that have a descendant that matches the selector. */
  def has(selector: String): Selection = js.native

  /** Get the HTML contents of the first element in the set of matched elements. */
  def html(): String = js.native

  /**
   * Check the current matched set of elements against a selector and return `true` if at least one
   * of these elements matches the given selector.
   */
  def is(selector: String): Boolean = js.native

  /** Reduce the set of matched elements to the final one in the set. */
  def last(): Selection = js.native

  /**
   * Pass each selection in the current matched set through a function, producing a new array
   * containing the return values.
   */
  def map(fn: js.Function2[Int, Element, Any]): js.Array[Any] = js.native

  /**
   * Get the immediately following sibling of each element in the set of matched elements,
   * optionally filtered by a selector.
   */
  def next(selector: js.UndefOr[String] = js.undefined): Selection = js.native

  /**
   * Get all following siblings of each element in the set of matched elements, optionally filtered
   * by a selector.
   */
  def nextAll(selector: js.UndefOr[String] = js.undefined): Selection = js.native

  /**
   * Get all following siblings of each element up to but not including the element matched by the
   * selector.
   */
  def nextUntil(
      selector: js.UndefOr[String] = js.undefined,
      filter: js.UndefOr[String] = js.undefined
  ): Selection = js.native

  /** Remove elements from the set of matched elements. */
  def not(selector: String): Selection = js.native

  /**
   * Get the parent of each element in the current set of matched elements, optionally filtered by
   * a selector.
   */
  def parent(selector: js.UndefOr[String] = js.undefined): Selection = js.native

  /**
   * Get the ancestors of each element in the current set of matched elements, optionally filtered
   * by a selector.
   */
  def parents(selector: js.UndefOr[String] = js.undefined): Selection = js.native

  /**
   * Get the ancestors of each element in the current set of matched elements, up to but not
   * including the element matched by the selector.
   */
  def parentsUntil(
      selector: js.UndefOr[String] = js.undefined,
      filter: js.UndefOr[String] = js.undefined
  ): Selection = js.native

  /**
   * Get all preceding siblings of each element in the set of matched elements, optionally filtered
   * by a selector.
   */
  def prevAll(selector: js.UndefOr[String] = js.undefined): Selection = js.native

  /**
   * Get the immediately preceding sibling of each element in the set of matched elements,
   * optionally filtered by a selector.
   */
  def prev(selector: js.UndefOr[String] = js.undefined): Selection = js.native

  /**
   * Get all preceding siblings of each element up to but not including the element matched by the
   * selector.
   */
  def prevUntil(
      selector: js.UndefOr[String] = js.undefined,
      filter: js.UndefOr[String] = js.undefined
  ): Selection = js.native

  /** Encode a set of form elements as a URL-encoded string. */
  def serialize(): String = js.native

  /** Encode a set of form elements as an array of name/value objects. */
  def serializeArray(): js.Array[Selection.NameValue] = js.native

  /** Encode a set of form elements as an object mapping names to values. */
  def serializeObject(): js.Dictionary[String] = js.native

  /** Return the number of elements in the Selection. */
  def size(): Int = js.native

  /**
   * Reduce the set of matched elements to a subset specified by a range of indices.
   *
   * @param start
   *   zero-based start index (inclusive)
   * @param end
   *   optional end index (exclusive)
   */
  def slice(start: Int, end: js.UndefOr[Int] = js.undefined): Selection = js.native

  /** Get the text content of the selection. */
  def text(): String = js.native

  /** Retrieve all elements contained in the Selection as an array. */
  def toArray(): js.Array[Element] = js.native

  /**
   * Get the current value of the first element in the set of matched elements.
   *
   * Returns `undefined` when there is no value.
   */
  def `val`(): js.UndefOr[String] = js.native
}

object Selection {

  /**
   * Name/value pair used by [[Selection.serializeArray]].
   *
   * Mirrors the `{ name: string, value: string }` structure from the k6/html API.
   */
  @js.native
  trait NameValue extends js.Object {
    def name: String = js.native
    def value: String = js.native
  }
}
