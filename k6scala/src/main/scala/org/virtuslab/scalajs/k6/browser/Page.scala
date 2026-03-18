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

import org.virtuslab.scalajs.k6.http.Response

/**
 * Facade for a single browser page (tab) in k6/browser.
 *
 * Provides navigation, content retrieval, waiting utilities, script evaluation, and screenshots.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-browser/page/ k6 Page]]
 */
@js.native
trait Page extends js.Object {

  /** Navigate to the given URL and resolve with the resulting HTTP [[Response]]. */
  def goto(
      url: String,
      options: js.UndefOr[NavigationOptions] = js.undefined
  ): js.Promise[Response] =
    js.native

  /** Reload the current page, optionally controlling timeout and waitUntil state. */
  def reload(options: js.UndefOr[NavigationOptions] = js.undefined): js.Promise[Response] =
    js.native

  /** Navigate back in the browser history if possible. */
  def goBack(options: js.UndefOr[NavigationOptions] = js.undefined): js.Promise[Response] =
    js.native

  /** Navigate forward in the browser history if possible. */
  def goForward(options: js.UndefOr[NavigationOptions] = js.undefined): js.Promise[Response] =
    js.native

  /** Returns the full HTML content of the current page. */
  def content(): js.Promise[String] = js.native

  /** Returns the current page title. */
  def title(): js.Promise[String] = js.native

  /** Returns the current page URL. */
  def url(): String = js.native

  /**
   * Waits for a navigation to complete according to the specified options and resolves with the
   * navigation [[Response]].
   */
  def waitForNavigation(
      options: js.UndefOr[WaitForNavigationOptions] = js.undefined
  ): js.Promise[Response] = js.native

  /**
   * Waits for an element matching the given CSS selector to appear on the page.
   *
   * Returns a promise that resolves when the condition is met or rejects on timeout.
   */
  def waitForSelector(
      selector: String,
      options: js.UndefOr[WaitForSelectorOptions] = js.undefined
  ): js.Promise[Unit] = js.native

  /** Waits for the given timeout in milliseconds before resolving. */
  def waitForTimeout(timeout: Double): js.Promise[Unit] = js.native

  /**
   * Waits for the page to reach a specific load state, such as `"load"` or `"networkidle"`.
   */
  def waitForLoadState(
      state: js.UndefOr[String] = js.undefined,
      options: js.UndefOr[WaitForLoadStateOptions] = js.undefined
  ): js.Promise[Unit] = js.native

  /**
   * Waits for the provided JavaScript function to evaluate to a truthy value in the page context.
   *
   * @param expression
   *   JavaScript function expression or predicate string, e.g. `"() => document.readyState ===
   *   'complete'"`.
   * @param arg
   *   optional argument passed as the first parameter to the function
   */
  def waitForFunction(
      expression: String,
      arg: js.UndefOr[js.Any] = js.undefined,
      options: js.UndefOr[WaitForFunctionOptions] = js.undefined
  ): js.Promise[js.Any] = js.native

  /**
   * Evaluates the given JavaScript expression in the page context and resolves with the result.
   *
   * @param expression
   *   JavaScript function or expression string
   * @param arg
   *   optional argument passed as the first parameter to the function
   */
  def evaluate(expression: String, arg: js.UndefOr[js.Any] = js.undefined): js.Promise[js.Any] =
    js.native

  /**
   * Evaluates the given JavaScript expression in the page context and resolves with a handle to
   * the result.
   *
   * The concrete handle type is introduced in a later module and is currently modelled as
   * [[js.Any]].
   */
  def evaluateHandle(
      expression: String,
      arg: js.UndefOr[js.Any] = js.undefined
  ): js.Promise[js.Any] = js.native

  /**
   * Captures a screenshot of the page. When `path` is specified in [[ScreenshotOptions]], k6 saves
   * the screenshot to that path and also returns the binary image data as an [[ArrayBuffer]].
   */
  def screenshot(
      options: js.UndefOr[ScreenshotOptions] = js.undefined
  ): js.Promise[ArrayBuffer] = js.native

  /**
   * Creates a [[Locator]] scoped to elements matching the given selector on this page.
   *
   * Locators are the recommended way to perform interactions such as click, fill, and check.
   */
  def locator(selector: String): Locator = js.native
}

/**
 * Navigation options for [[Page.goto]], [[Page.reload]], [[Page.goBack]], and [[Page.goForward]].
 *
 * Mirrors the subset of k6/browser navigation options needed for core workflows.
 */
@js.native
trait NavigationOptions extends js.Object {
  def referer: js.UndefOr[String] = js.native
  def timeout: js.UndefOr[Double] = js.native
  def waitUntil: js.UndefOr[String] = js.native
}

object NavigationOptions {

  def apply(
      referer: Option[String] = None,
      timeout: Option[Double] = None,
      waitUntil: Option[String] = None
  ): NavigationOptions =
    js.Dynamic
      .literal(
        referer = referer.orUndefined,
        timeout = timeout.orUndefined,
        waitUntil = waitUntil.orUndefined
      )
      .asInstanceOf[NavigationOptions]
}

/**
 * Options for [[Page.waitForNavigation]].
 */
@js.native
trait WaitForNavigationOptions extends js.Object {
  def timeout: js.UndefOr[Double] = js.native
  def waitUntil: js.UndefOr[String] = js.native
}

object WaitForNavigationOptions {

  def apply(
      timeout: Option[Double] = None,
      waitUntil: Option[String] = None
  ): WaitForNavigationOptions =
    js.Dynamic
      .literal(
        timeout = timeout.orUndefined,
        waitUntil = waitUntil.orUndefined
      )
      .asInstanceOf[WaitForNavigationOptions]
}

/**
 * Options for [[Page.waitForSelector]].
 */
@js.native
trait WaitForSelectorOptions extends js.Object {
  def state: js.UndefOr[String] = js.native
  def timeout: js.UndefOr[Double] = js.native
}

object WaitForSelectorOptions {

  def apply(
      state: Option[String] = None,
      timeout: Option[Double] = None
  ): WaitForSelectorOptions =
    js.Dynamic
      .literal(
        state = state.orUndefined,
        timeout = timeout.orUndefined
      )
      .asInstanceOf[WaitForSelectorOptions]
}

/**
 * Options for [[Page.waitForLoadState]].
 */
@js.native
trait WaitForLoadStateOptions extends js.Object {
  def timeout: js.UndefOr[Double] = js.native
}

object WaitForLoadStateOptions {

  def apply(timeout: Option[Double] = None): WaitForLoadStateOptions =
    js.Dynamic
      .literal(
        timeout = timeout.orUndefined
      )
      .asInstanceOf[WaitForLoadStateOptions]
}

/**
 * Options for [[Page.waitForFunction]].
 */
@js.native
trait WaitForFunctionOptions extends js.Object {
  def timeout: js.UndefOr[Double] = js.native
}

object WaitForFunctionOptions {

  def apply(timeout: Option[Double] = None): WaitForFunctionOptions =
    js.Dynamic
      .literal(
        timeout = timeout.orUndefined
      )
      .asInstanceOf[WaitForFunctionOptions]
}

/**
 * Options for [[Page.screenshot]].
 *
 * Mirrors the common k6/browser screenshot configuration: output path, image type, quality, and
 * page rendering hints.
 */
@js.native
trait ScreenshotOptions extends js.Object {
  def path: js.UndefOr[String] = js.native
  def fullPage: js.UndefOr[Boolean] = js.native
  def `type`: js.UndefOr[String] = js.native
  def quality: js.UndefOr[Int] = js.native
  def omitBackground: js.UndefOr[Boolean] = js.native
}

object ScreenshotOptions {

  def apply(
      path: Option[String] = None,
      fullPage: Option[Boolean] = None,
      imageType: Option[String] = None,
      quality: Option[Int] = None,
      omitBackground: Option[Boolean] = None
  ): ScreenshotOptions =
    js.Dynamic
      .literal(
        path = path.orUndefined,
        fullPage = fullPage.orUndefined,
        `type` = imageType.orUndefined,
        quality = quality.orUndefined,
        omitBackground = omitBackground.orUndefined
      )
      .asInstanceOf[ScreenshotOptions]
}
