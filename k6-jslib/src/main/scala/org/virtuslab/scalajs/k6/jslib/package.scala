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

package org.virtuslab.scalajs.k6

package object jslib {

  /**
   * Facades and utilities for the k6 jslib ecosystem.
   *
   * This module targets the `k6-utils` jslib library at version 1.2.0 and
   * is compiled as a Scala.js ES module so that k6 can execute the generated
   * JavaScript directly with URL-based imports.
   *
   * All public APIs under this package are versioned alongside `k6-scala`, but
   * their semantics are defined by the corresponding jslib (currently
   * `k6-utils` 1.2.0).
   */
}
