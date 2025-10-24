package org.virtuslab.scalajs.converters

import scala.scalajs.js

trait FromJSPromise[F[_]]{
  def apply[A](p: => js.Promise[A]): F[A]
}