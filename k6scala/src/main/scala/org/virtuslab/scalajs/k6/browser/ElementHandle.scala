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

package org.virtuslab.scalajs.k6.browser

import scala.scalajs.js
import scala.scalajs.js.typedarray.ArrayBuffer

/**
 * Facade for a concrete DOM element handle in k6/browser.
 *
 * Element handles represent a specific element at a point in time. Prefer using [[Locator]] where
 * possible, which automatically re-resolves elements between actions.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-browser/elementhandle/ k6
 *   ElementHandle]]
 */
@js.native
trait ElementHandle extends js.Object {

  def click(options: js.UndefOr[ClickOptions] = js.undefined): js.Promise[Unit] = js.native

  def dblclick(options: js.UndefOr[ClickOptions] = js.undefined): js.Promise[Unit] = js.native

  def fill(value: String, options: js.UndefOr[FillOptions] = js.undefined): js.Promise[Unit] =
    js.native

  def `type`(text: String, options: js.UndefOr[FillOptions] = js.undefined): js.Promise[Unit] =
    js.native

  def check(options: js.UndefOr[FillOptions] = js.undefined): js.Promise[Unit] = js.native

  def uncheck(options: js.UndefOr[FillOptions] = js.undefined): js.Promise[Unit] = js.native

  def selectOption(
      values: js.UndefOr[js.Any] = js.undefined,
      options: js.UndefOr[SelectOptionOptions] = js.undefined
  ): js.Promise[Unit] = js.native

  def press(key: String, options: js.UndefOr[js.Any] = js.undefined): js.Promise[Unit] = js.native

  def hover(options: js.UndefOr[ClickOptions] = js.undefined): js.Promise[Unit] = js.native

  def focus(): js.Promise[Unit] = js.native

  def blur(): js.Promise[Unit] = js.native

  def isVisible(): js.Promise[Boolean] = js.native

  def isEnabled(): js.Promise[Boolean] = js.native

  def isDisabled(): js.Promise[Boolean] = js.native

  def isEditable(): js.Promise[Boolean] = js.native

  def isChecked(): js.Promise[Boolean] = js.native

  def textContent(): js.Promise[js.UndefOr[String]] = js.native

  def innerText(): js.Promise[String] = js.native

  def innerHTML(): js.Promise[String] = js.native

  def inputValue(): js.Promise[String] = js.native

  def getAttribute(name: String): js.Promise[js.UndefOr[String]] = js.native

  def screenshot(
      options: js.UndefOr[ScreenshotOptions] = js.undefined
  ): js.Promise[ArrayBuffer] = js.native
}

