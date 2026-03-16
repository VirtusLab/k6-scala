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
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.typedarray.ArrayBuffer

/**
 * Facade for a k6/browser Locator.
 *
 * Locators are the preferred way to find and interact with elements on a [[Page]].
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-browser/locator/ k6 Locator]]
 */
@js.native
trait Locator extends js.Object {

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

  def waitFor(options: js.UndefOr[WaitForOptions] = js.undefined): js.Promise[Unit] = js.native
}

/**
 * Options for click-like interactions on a [[Locator]] or [[ElementHandle]].
 */
@js.native
trait ClickOptions extends js.Object {
  def button: js.UndefOr[String] = js.native
  def clickCount: js.UndefOr[Int] = js.native
  def delay: js.UndefOr[Double] = js.native
  def timeout: js.UndefOr[Double] = js.native
  def force: js.UndefOr[Boolean] = js.native
}

object ClickOptions {

  def apply(
      button: Option[String] = None,
      clickCount: Option[Int] = None,
      delay: Option[Double] = None,
      timeout: Option[Double] = None,
      force: Option[Boolean] = None
  ): ClickOptions =
    js.Dynamic
      .literal(
        button = button.orUndefined,
        clickCount = clickCount.orUndefined,
        delay = delay.orUndefined,
        timeout = timeout.orUndefined,
        force = force.orUndefined
      )
      .asInstanceOf[ClickOptions]
}

/**
 * Options for text input and checkbox interactions.
 */
@js.native
trait FillOptions extends js.Object {
  def timeout: js.UndefOr[Double] = js.native
  def noWaitAfter: js.UndefOr[Boolean] = js.native
}

object FillOptions {

  def apply(
      timeout: Option[Double] = None,
      noWaitAfter: Option[Boolean] = None
  ): FillOptions =
    js.Dynamic
      .literal(
        timeout = timeout.orUndefined,
        noWaitAfter = noWaitAfter.orUndefined
      )
      .asInstanceOf[FillOptions]
}

/**
 * Options for select element interactions.
 */
@js.native
trait SelectOptionOptions extends js.Object {
  def timeout: js.UndefOr[Double] = js.native
  def noWaitAfter: js.UndefOr[Boolean] = js.native
}

object SelectOptionOptions {

  def apply(
      timeout: Option[Double] = None,
      noWaitAfter: Option[Boolean] = None
  ): SelectOptionOptions =
    js.Dynamic
      .literal(
        timeout = timeout.orUndefined,
        noWaitAfter = noWaitAfter.orUndefined
      )
      .asInstanceOf[SelectOptionOptions]
}

/**
 * Options for [[Locator.waitFor]].
 */
@js.native
trait WaitForOptions extends js.Object {
  def state: js.UndefOr[String] = js.native
  def timeout: js.UndefOr[Double] = js.native
}

object WaitForOptions {

  def apply(
      state: Option[String] = None,
      timeout: Option[Double] = None
  ): WaitForOptions =
    js.Dynamic
      .literal(
        state = state.orUndefined,
        timeout = timeout.orUndefined
      )
      .asInstanceOf[WaitForOptions]
}

