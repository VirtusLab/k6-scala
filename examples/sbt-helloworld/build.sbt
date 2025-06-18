import scala.sys.process._
import org.scalajs.linker.interface.ModuleSplitStyle

import sbt.Keys._
import sbt._

publishMavenStyle := true

lazy val runK6test = taskKey[Unit]("runs k6 perf test")

lazy val helloworld = exampleProject("helloworld", ".")

def exampleProject(id: String, dir: String) = Project(id, file(dir))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    scalaVersion := "3.5.0",
    publish / skip := true,
    scalaJSLinkerConfig ~= {
      _.withModuleSplitStyle(ModuleSplitStyle.FewestModules).withModuleKind(ModuleKind.ESModule)
    },
    runK6test := {
      val jsTargetDir = (Compile / fullOptJS / crossTarget).value
      val name = (Compile / fullOptJS / moduleName).value
      val jsOutput = jsTargetDir / s"${name}-fastopt.js"
      val log = streams.value.log
      log.info(s"Using JS output: ${jsOutput.getAbsolutePath()}")
      log.info(s"Running k6 test...")
      val exitCode = Process(Seq("k6", "run", jsOutput.getAbsolutePath)).!
      if (exitCode != 0) {
        log.error("k6 failed!")
      }
    },
    libraryDependencies ++= Seq(
      "org.scalajs" %%% "k6-scala" % "0.0.1-SNAPSHOT"
    )
  )
