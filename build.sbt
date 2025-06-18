import scala.sys.process._
import org.scalajs.linker.interface.ModuleSplitStyle

import sbt.Keys._
import sbt._

ThisBuild / organization  := "com.github.wiacekm"

publishMavenStyle := true

//settings for all the projects
lazy val commonSettings = Seq(
  scalaVersion := "3.5.0",
  crossScalaVersions := Seq("2.12.10", "2.13.16", "3.3.6"),
  name := "k6-scala",
  updateOptions := updateOptions.value.withCachedResolution(true),
  scalacOptions ++= Seq(
    "-deprecation"
  ),
  libraryDependencies ++= Seq(
    "org.scalatest" %%% "scalatest" % "3.2.19" % Test
  )
)

lazy val k6scala: Project =
  Project(id = "k6scala", base = file("k6scala"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(commonSettings: _*)

lazy val root = Project("root", file("."))
  .settings(commonSettings)
  .dependsOn(k6scala)
  .aggregate(k6scala)
