import scala.sys.process._
import org.scalajs.linker.interface.ModuleSplitStyle

import sbt.Keys._
import sbt._

ThisBuild / organization := "org.virtuslab"

ThisBuild / licenses := Seq(("Apache-2.0" -> url("https://opensource.org/licenses/apache-2-0")))
ThisBuild / description := "Type-safe Scala.js facade for writing k6 load testing scenarios in Scala."
ThisBuild / homepage := Some(url("https://github.com/VirtusLab/k6-scala"))
ThisBuild / developers := List(
  Developer("wiacekm", "Michał Wiącek", "", url("https://github.com/wiacekm"))
)
ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/VirtusLab/k6-scala"),
    "scm:git:git@github.com:VirtusLab/k6-scala.git"
  )
)

// Detect a custom tag/property, e.g., -Drelease=dev
lazy val releaseTag = sys.props.get("release")

//settings for all the projects
lazy val commonSettings = Seq(
  scalaVersion := "3.5.0",
  crossScalaVersions := Seq("2.12.21", "2.13.18", "3.3.6", "3.5.0"),
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
    .settings(commonSettings ++ crossCompileSettings("k6scala"): _*)

lazy val publishLocalDev = taskKey[Unit]("Publish local version with dev suffix")

publishLocalDev := {
  val log = streams.value.log
  val s = (state.value: @sbtUnchecked)
  val devVersion = "dev"
  val versions =
    ((k6scala / crossScalaVersions).value :+ (k6scala / scalaVersion).value).distinct

  log.info(
    s"Publishing dev version: $devVersion for Scala versions: ${versions.mkString(", ")}"
  )

  versions.foreach { ver =>
    val extracted = Project.extract(s)
    val newState = extracted.appendWithoutSession(
      Seq(
        ThisBuild / version := devVersion,
        ThisBuild / scalaVersion := ver
      ),
      s
    )
    Project.extract(newState).runTask(k6scala / publishLocal, newState)
  }
}

lazy val root = Project("root", file("."))
  .settings(commonSettings)
  .dependsOn(k6scala)
  .aggregate(k6scala)

def crossCompileSettings(module: String) =
  Seq(
    Compile / unmanagedSourceDirectories += {
      val sharedSourceDir = (ThisBuild / baseDirectory).value / s"$module/src/main"
      val data = CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((3, _)) =>
          sharedSourceDir / "scala-3"
        case Some((2, 13)) =>
          sharedSourceDir / "scala-2.13"
        case _ =>
          sharedSourceDir / "scala-2.12"
      }
      println(data)
      data
    },
    Test / unmanagedSourceDirectories += {
      val sharedSourceDir = (ThisBuild / baseDirectory).value / s"$module/src/test"
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((3, _)) =>
          sharedSourceDir / "scala-3"
        case Some((2, 13)) =>
          sharedSourceDir / "scala-2.13"
        case _ =>
          sharedSourceDir / "scala-2.12"
      }
    }
  )
