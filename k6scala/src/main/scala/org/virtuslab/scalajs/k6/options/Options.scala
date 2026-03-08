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

package org.virtuslab.scalajs.k6.options

import org.virtuslab.scalajs.k6.http.CipherSuiteType
import scala.scalajs.js
import scala.scalajs.js.|
import scala.scalajs.js.JSConverters._

// TODO: make sure it cannot be used with iterations by making sealed classes.
@js.native
trait Options extends js.Object {
  def batch: js.UndefOr[Int] = js.native
  def batchPerHost: js.UndefOr[Int] = js.native
  def blacklistIPs: js.UndefOr[js.Array[String]] = js.native
  def blockHostnames: js.UndefOr[js.Array[String]] = js.native
  def discardResponseBodies: js.UndefOr[Boolean] = js.native
  def dns: js.UndefOr[DnsOption] = js.native
  def duration: js.UndefOr[String] = js.native
  def executionSegment: js.UndefOr[String] = js.native
  def executionSegmentSequence: js.UndefOr[String] = js.native
  def ext: js.UndefOr[js.Dictionary[CollectorOptions]] = js.native
  def cloud: js.UndefOr[CloudOptions] = js.native
  def hosts: js.UndefOr[js.Dictionary[String]] = js.native
  def httpDebug: js.UndefOr[String] = js.native
  def insecureSkipTLSVerify: js.UndefOr[Boolean] = js.native
  def iterations: js.UndefOr[Int] = js.native
  def linger: js.UndefOr[Boolean] = js.native
  def maxRedirects: js.UndefOr[Int] = js.native
  def minIterationDuration: js.UndefOr[String] = js.native
  def noConnectionReuse: js.UndefOr[Boolean] = js.native
  def noCookiesReset: js.UndefOr[Boolean] = js.native
  def noUsageReport: js.UndefOr[Boolean] = js.native
  def noVUConnectionReuse: js.UndefOr[Boolean] = js.native
  def paused: js.UndefOr[Boolean] = js.native
  def rps: js.UndefOr[Int] = js.native
  def scenarios: js.UndefOr[js.Dictionary[Scenario]] = js.native
  def setupTimeout: js.UndefOr[String] = js.native
  def stages: js.UndefOr[Stage] = js.native
  def summaryTrendStats: js.UndefOr[js.Array[String]] = js.native
  def systemTags: js.UndefOr[js.Array[String]] = js.native
  def tags: js.UndefOr[js.Dictionary[String]] = js.native
  def teardownTimeout: js.UndefOr[String] = js.native
  def thresholds: js.UndefOr[js.Dictionary[js.Array[String | ObjectThreshold]]] = js.native
  def `throw`: js.UndefOr[Boolean] = js.native
  def tlsAuth: js.UndefOr[js.Array[Certificate]] = js.native
  def tlsCipherSuites: js.UndefOr[js.Array[CipherSuiteType]] = js.native
  def tlsVersion: js.UndefOr[String | TtlVersionRange] = js.native
  def userAgent: js.UndefOr[String] = js.native
  def vus: js.UndefOr[Int] = js.native
  def vusMax: js.UndefOr[Int] = js.native
}

object Options {
  def apply(
      batch: Option[Int] = None,
      batchPerHost: Option[Int] = None,
      blacklistIPs: Option[js.Array[String]] = None,
      blockHostnames: Option[js.Array[String]] = None,
      discardResponseBodies: Option[Boolean] = None,
      dns: Option[DnsOption] = None,
      duration: Option[String] = None,
      executionSegment: Option[String] = None,
      executionSegmentSequence: Option[String] = None,
      ext: Option[js.Dictionary[CollectorOptions]] = None,
      cloud: Option[CloudOptions] = None,
      hosts: Option[js.Dictionary[String]] = None,
      httpDebug: Option[String] = None,
      insecureSkipTLSVerify: Option[Boolean] = None,
      iterations: Option[Int] = None,
      linger: Option[Boolean] = None,
      maxRedirects: Option[Int] = None,
      minIterationDuration: Option[String] = None,
      noConnectionReuse: Option[Boolean] = None,
      noCookiesReset: Option[Boolean] = None,
      noUsageReport: Option[Boolean] = None,
      noVUConnectionReuse: Option[Boolean] = None,
      paused: Option[Boolean] = None,
      rps: Option[Int] = None,
      scenarios: Option[Map[String, Scenario]] = None,
      setupTimeout: Option[String] = None,
      stages: Option[Stage] = None,
      summaryTrendStats: Option[js.Array[String]] = None,
      systemTags: Option[js.Array[String]] = None,
      tags: Option[js.Dictionary[String]] = None,
      teardownTimeout: Option[String] = None,
      thresholds: Option[js.Dictionary[js.Array[String | ObjectThreshold]]] = None,
      `throw`: Option[Boolean] = None,
      tlsAuth: Option[js.Array[Certificate]] = None,
      tlsCipherSuites: Option[js.Array[CipherSuiteType]] = None,
      tlsVersion: Option[String | TtlVersionRange] = None,
      userAgent: Option[String] = None,
      vus: Option[Int] = None,
      vusMax: Option[Int] = None
  ): Options = {
    js.Dynamic
      .literal(
        batch = batch.orUndefined,
        batchPerHost = batchPerHost.orUndefined,
        blacklistIPs = blacklistIPs.orUndefined,
        blockHostnames = blockHostnames.orUndefined,
        discardResponseBodies = discardResponseBodies.orUndefined,
        dns = dns.orUndefined,
        duration = duration.orUndefined,
        executionSegment = executionSegment.orUndefined,
        executionSegmentSequence = executionSegmentSequence.orUndefined,
        ext = ext.orUndefined,
        cloud = cloud.orUndefined,
        hosts = hosts.orUndefined,
        httpDebug = httpDebug.orUndefined,
        insecureSkipTLSVerify = insecureSkipTLSVerify.orUndefined,
        iterations = iterations.orUndefined,
        linger = linger.orUndefined,
        maxRedirects = maxRedirects.orUndefined,
        minIterationDuration = minIterationDuration.orUndefined,
        noConnectionReuse = noConnectionReuse.orUndefined,
        noCookiesReset = noCookiesReset.orUndefined,
        noUsageReport = noUsageReport.orUndefined,
        noVUConnectionReuse = noVUConnectionReuse.orUndefined,
        paused = paused.orUndefined,
        rps = rps.orUndefined,
        scenarios = scenarios.map(_.toJSDictionary).orUndefined,
        setupTimeout = setupTimeout.orUndefined,
        stages = stages.orUndefined,
        summaryTrendStats = summaryTrendStats.orUndefined,
        systemTags = systemTags.orUndefined,
        tags = tags.orUndefined,
        teardownTimeout = teardownTimeout.orUndefined,
        thresholds = thresholds.orUndefined,
        `throw` = `throw`.orUndefined,
        tlsAuth = tlsAuth.orUndefined,
        tlsCipherSuites = tlsCipherSuites.orUndefined,
        tlsVersion =
          tlsVersion.fold(())(identity[String | TtlVersionRange](_)).asInstanceOf[js.Any],
        userAgent = userAgent.orUndefined,
        vus = vus.orUndefined,
        vusMax = vusMax.orUndefined
      )
      .asInstanceOf[Options]
  }

}
