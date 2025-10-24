scalacOptions ++= Seq("-unchecked", "-deprecation")

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.18.1")
addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.21.1")
addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.5.12")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.2")
