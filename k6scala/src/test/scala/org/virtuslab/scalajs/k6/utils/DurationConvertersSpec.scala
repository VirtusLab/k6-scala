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

package org.virtuslab.scalajs.k6.utils

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.virtuslab.scalajs.k6.utils.DurationConverters._

import scala.concurrent.duration._

class DurationToK6Spec extends AnyFlatSpec with Matchers {

  it should "correctly format known values" in {
    (1.second).toK6 shouldBe "1s"
    (500.milliseconds).toK6 shouldBe "500ms"
    (2.minutes + 5.seconds).toK6 shouldBe "2m5s"
    (1.hour + 30.minutes + 20.seconds).toK6 shouldBe "1h30m20s"
    (1.microsecond).toK6 shouldBe "1us"
    (3.nanoseconds).toK6 shouldBe "3ns"
  }
}
