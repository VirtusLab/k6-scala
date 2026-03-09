import mill._
import mill.api.Loose
import mill.scalalib._
import mill.scalajslib._
import mill.scalajslib.api._

val projectScalaVersion = "3.5.0"

object helloworld extends ScalaJSModule {

  override def scalaVersion = projectScalaVersion
  override def scalaJSVersion = "1.20.2"

  override def ivyDeps: Target[Loose.Agg[Dep]] = super.ivyDeps() ++ Agg(
    ivy"org.virtuslab::k6-scala::dev"
  )
  override def moduleKind = ModuleKind.ESModule
  override def moduleSplitStyle = ModuleSplitStyle.FewestModules

  override def sources = T.sources {
    println(millSourcePath)
    Seq(millSourcePath / "src").map(PathRef(_))
  }

  def runK6test() = T.command {
    val jsFilePath: os.Path = fastLinkJS().dest.path / "main.js"
    println(s"Using JS output: $jsFilePath")
    val exitCode =
      os.proc("k6", "run", jsFilePath).call(stdout = os.Inherit, stderr = os.Inherit).exitCode
    if (exitCode != 0) {
      throw new Exception("k6 failed!")
    }
  }

}
