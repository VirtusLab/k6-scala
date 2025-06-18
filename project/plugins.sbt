scalacOptions ++= Seq("-unchecked", "-deprecation")

addSbtPlugin("org.scala-js"  % "sbt-scalajs"         % "1.18.1")
addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.21.1")
addSbtPlugin("ch.epfl.scala" % "sbt-release-early"   % "2.1.1")
addSbtPlugin("org.scalameta" % "sbt-scalafmt"        % "2.5.2")
