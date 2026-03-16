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
 * BrowserContext represents an isolated browser session with its own pages, cache, and cookies.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-browser/browsercontext/ k6 BrowserContext]]
 */
@js.native
trait BrowserContext extends js.Object {

  /** Creates a new Page in this context and returns it. */
  def newPage(): js.Promise[js.Any] = js.native

  /** Returns all pages that belong to this context. */
  def pages(): js.Promise[js.Array[js.Any]] = js.native

  /** Closes the context and all of its pages. */
  def close(): js.Promise[Unit] = js.native

  /** Adds cookies to the context; all pages will see these cookies. */
  def addCookies(cookies: js.Array[BrowserCookie]): js.Promise[Unit] = js.native

  /** Clears all cookies from the context. */
  def clearCookies(): js.Promise[Unit] = js.native

  /**
   * Returns cookies filtered by the optional list of URLs. With no arguments, returns all cookies.
   */
  def cookies(urls: String*): js.Promise[js.Array[BrowserCookie]] = js.native

  /**
   * Grants the specified permissions for this context.
   *
   * @param permissions
   *   permission names, e.g. `"geolocation"`, `"notifications"`
   * @param options
   *   optional origin scoping
   */
  def grantPermissions(
      permissions: js.Array[String],
      options: js.UndefOr[GrantPermissionsOptions] = js.undefined
  ): js.Promise[Unit] = js.native

  /** Clears all permission overrides on this context. */
  def clearPermissions(): js.Promise[Unit] = js.native

  /** Sets the default navigation timeout in milliseconds. */
  def setDefaultNavigationTimeout(timeout: Double): js.Promise[Unit] = js.native

  /** Sets the default timeout for methods that accept a timeout in milliseconds. */
  def setDefaultTimeout(timeout: Double): js.Promise[Unit] = js.native

  /** Sets the geolocation for this context. */
  def setGeolocation(geolocation: Geolocation): js.Promise[Unit] = js.native

  /** Toggles offline mode for this context. */
  def setOffline(offline: Boolean): js.Promise[Unit] = js.native

  /**
   * Waits for an event to fire and resolves with the event payload.
   *
   * @param event
   *   event name, e.g. `"page"`, `"close"`
   * @param optionsOrPredicate
   *   optional predicate or options object as in the k6 API
   */
  def waitForEvent(
      event: String,
      optionsOrPredicate: js.UndefOr[js.Any] = js.undefined
  ): js.Promise[js.Any] = js.native
}

/**
 * Cookie structure used by [[BrowserContext.addCookies]] and [[BrowserContext.cookies]].
 *
 * Mirrors the k6 BrowserContext cookie type.
 *
 * @see
 *   [[https://grafana.com/docs/k6/latest/javascript-api/k6-browser/browsercontext/cookie/ k6 cookie]]
 */
@js.native
trait BrowserCookie extends js.Object {
  def name: String = js.native
  def value: String = js.native
  def domain: js.UndefOr[String] = js.native
  def path: js.UndefOr[String] = js.native
  def url: js.UndefOr[String] = js.native
  def httpOnly: js.UndefOr[Boolean] = js.native
  def secure: js.UndefOr[Boolean] = js.native
  def sameSite: js.UndefOr[String] = js.native
  def expires: js.UndefOr[Double] = js.native
}

object BrowserCookie {

  def apply(
      name: String,
      value: String,
      domain: Option[String] = None,
      path: Option[String] = None,
      url: Option[String] = None,
      httpOnly: Option[Boolean] = None,
      secure: Option[Boolean] = None,
      sameSite: Option[String] = None,
      expires: Option[Double] = None
  ): BrowserCookie =
    js.Dynamic
      .literal(
        name = name,
        value = value,
        domain = domain.orUndefined,
        path = path.orUndefined,
        url = url.orUndefined,
        httpOnly = httpOnly.orUndefined,
        secure = secure.orUndefined,
        sameSite = sameSite.orUndefined,
        expires = expires.orUndefined
      )
      .asInstanceOf[BrowserCookie]
}

/**
 * Options for [[BrowserContext.grantPermissions]].
 *
 * @param origin
 *   Optional origin to scope the granted permissions to.
 */
@js.native
trait GrantPermissionsOptions extends js.Object {
  def origin: js.UndefOr[String] = js.native
}

object GrantPermissionsOptions {

  def apply(origin: Option[String] = None): GrantPermissionsOptions =
    js.Dynamic
      .literal(
        origin = origin.orUndefined
      )
      .asInstanceOf[GrantPermissionsOptions]
}

