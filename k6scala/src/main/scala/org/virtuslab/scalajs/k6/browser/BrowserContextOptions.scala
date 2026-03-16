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

/**
 * Options object for [[Browser.newContext]] and [[Browser.newPage]].
 *
 * Mirrors the JavaScript `newContext` / `newPage` options structure from k6/browser.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-browser/newcontext/ k6 newContext]]
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-browser/newpage/ k6 newPage]]
 */
@js.native
trait BrowserContextOptions extends js.Object {
  def bypassCSP: js.UndefOr[Boolean] = js.native
  def colorScheme: js.UndefOr[String] = js.native
  def deviceScaleFactor: js.UndefOr[Double] = js.native
  def extraHTTPHeaders: js.UndefOr[js.Dictionary[String]] = js.native
  def geolocation: js.UndefOr[Geolocation] = js.native
  def hasTouch: js.UndefOr[Boolean] = js.native
  def httpCredentials: js.UndefOr[HttpCredentials] = js.native
  def ignoreHTTPSErrors: js.UndefOr[Boolean] = js.native
  def isMobile: js.UndefOr[Boolean] = js.native
  def javaScriptEnabled: js.UndefOr[Boolean] = js.native
  def locale: js.UndefOr[String] = js.native
  def offline: js.UndefOr[Boolean] = js.native
  def permissions: js.UndefOr[js.Array[String]] = js.native
  def reducedMotion: js.UndefOr[String] = js.native
  def screen: js.UndefOr[ScreenSize] = js.native
  def timezoneID: js.UndefOr[String] = js.native
  def userAgent: js.UndefOr[String] = js.native
  def viewport: js.UndefOr[ViewportSize] = js.native
}

object BrowserContextOptions {

  def apply(
      bypassCSP: Option[Boolean] = None,
      colorScheme: Option[String] = None,
      deviceScaleFactor: Option[Double] = None,
      extraHTTPHeaders: Option[Map[String, String]] = None,
      geolocation: Option[Geolocation] = None,
      hasTouch: Option[Boolean] = None,
      httpCredentials: Option[HttpCredentials] = None,
      ignoreHTTPSErrors: Option[Boolean] = None,
      isMobile: Option[Boolean] = None,
      javaScriptEnabled: Option[Boolean] = None,
      locale: Option[String] = None,
      offline: Option[Boolean] = None,
      permissions: Option[Seq[String]] = None,
      reducedMotion: Option[String] = None,
      screen: Option[ScreenSize] = None,
      timezoneID: Option[String] = None,
      userAgent: Option[String] = None,
      viewport: Option[ViewportSize] = None
  ): BrowserContextOptions =
    js.Dynamic
      .literal(
        bypassCSP = bypassCSP.orUndefined,
        colorScheme = colorScheme.orUndefined,
        deviceScaleFactor = deviceScaleFactor.orUndefined,
        extraHTTPHeaders = extraHTTPHeaders.map(_.toJSDictionary).orUndefined,
        geolocation = geolocation.orUndefined,
        hasTouch = hasTouch.orUndefined,
        httpCredentials = httpCredentials.orUndefined,
        ignoreHTTPSErrors = ignoreHTTPSErrors.orUndefined,
        isMobile = isMobile.orUndefined,
        javaScriptEnabled = javaScriptEnabled.orUndefined,
        locale = locale.orUndefined,
        offline = offline.orUndefined,
        permissions = permissions.map(_.toJSArray).orUndefined,
        reducedMotion = reducedMotion.orUndefined,
        screen = screen.orUndefined,
        timezoneID = timezoneID.orUndefined,
        userAgent = userAgent.orUndefined,
        viewport = viewport.orUndefined
      )
      .asInstanceOf[BrowserContextOptions]
}

/**
 * Geolocation settings used in [[BrowserContextOptions]] and [[BrowserContext.setGeolocation]].
 */
@js.native
trait Geolocation extends js.Object {
  def latitude: Double = js.native
  def longitude: Double = js.native
  def accuracy: js.UndefOr[Double] = js.native
}

object Geolocation {

  def apply(
      latitude: Double,
      longitude: Double,
      accuracy: Option[Double] = None
  ): Geolocation =
    js.Dynamic
      .literal(
        latitude = latitude,
        longitude = longitude,
        accuracy = accuracy.orUndefined
      )
      .asInstanceOf[Geolocation]
}

/**
 * HTTP Basic authentication credentials for [[BrowserContextOptions]].
 */
@js.native
trait HttpCredentials extends js.Object {
  def username: String = js.native
  def password: String = js.native
}

object HttpCredentials {

  def apply(username: String, password: String): HttpCredentials =
    js.Dynamic
      .literal(
        username = username,
        password = password
      )
      .asInstanceOf[HttpCredentials]
}

/**
 * Viewport size in CSS pixels.
 */
@js.native
trait ViewportSize extends js.Object {
  def width: Int = js.native
  def height: Int = js.native
}

object ViewportSize {

  def apply(width: Int, height: Int): ViewportSize =
    js.Dynamic
      .literal(
        width = width,
        height = height
      )
      .asInstanceOf[ViewportSize]
}

/**
 * Screen size in physical pixels.
 */
@js.native
trait ScreenSize extends js.Object {
  def width: Int = js.native
  def height: Int = js.native
}

object ScreenSize {

  def apply(width: Int, height: Int): ScreenSize =
    js.Dynamic
      .literal(
        width = width,
        height = height
      )
      .asInstanceOf[ScreenSize]
}

