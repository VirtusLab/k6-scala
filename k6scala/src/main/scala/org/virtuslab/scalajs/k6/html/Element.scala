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
 * DOM Element facade used by the k6/html module.
 *
 * Instances of this type are returned from [[Selection.get]] and various Element navigation
 * methods. The API is intentionally close to the browser DOM `Element` interface but restricted to
 * read‑only operations, matching k6/html behaviour.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-html/element/ k6 Element API]]
 */
@js.native
trait Element extends js.Object {

  def selection(): Selection = js.native

  def nodeName(): String = js.native
  def nodeType(): Int    = js.native
  def nodeValue(): String = js.native

  def id(): String          = js.native
  def innerHTML(): String   = js.native
  def textContent(): String = js.native

  def ownerDocument(): Element = js.native

  def attributes(): js.Array[Element] = js.native

  def firstChild(): Element  = js.native
  def lastChild(): Element   = js.native
  def childElementCount(): Int = js.native

  def firstElementChild(): Element = js.native
  def lastElementChild(): Element  = js.native

  def previousSibling(): Element        = js.native
  def nextSibling(): Element            = js.native
  def previousElementSibling(): Element = js.native
  def nextElementSibling(): Element     = js.native

  def parentElement(): Element = js.native
  def parentNode(): Element    = js.native

  def childNodes(): js.Array[Element] = js.native
  def children(): js.Array[Element]   = js.native

  def classList(): js.Array[String] = js.native
  def className(): String           = js.native

  def lang(): String = js.native

  override def toString(): String = js.native

  def hasAttribute(name: String): Boolean = js.native
  def getAttribute(name: String): String  = js.native
  def hasAttributes(): Boolean            = js.native
  def hasChildNodes(): Boolean            = js.native

  def isSameNode(other: Element): Boolean  = js.native
  def isEqualNode(other: Element): Boolean = js.native

  def getElementsByClassName(name: String): js.Array[Element] = js.native
  def getElementsByTagName(name: String): js.Array[Element]   = js.native

  def querySelector(selector: String): Element              = js.native
  def querySelectorAll(selector: String): js.Array[Element] = js.native

  def contains(other: Element): Boolean = js.native

  def matches(selector: String): Boolean = js.native

  def namespaceURI(): String              = js.native
  def isDefaultNamespace(ns: String): Boolean = js.native
}

