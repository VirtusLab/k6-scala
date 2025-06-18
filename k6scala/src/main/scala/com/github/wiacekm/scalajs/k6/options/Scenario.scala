package com.github.wiacekm.scalajs.k6.options
import scala.scalajs.js
import scala.concurrent.duration.FiniteDuration
import com.github.wiacekm.scalajs.k6.utils.DurationConverters._
import js.JSConverters._

@js.native
trait BaseScenario extends js.Object {
  def executor: ExecutorOptionsType
  def startTime: js.UndefOr[String] = js.native
  def gracefulStop: js.UndefOr[String] = js.native
  def exec: js.UndefOr[String] = js.native
  def env: js.UndefOr[js.Dictionary[String]] = js.native
  def tags: js.UndefOr[js.Dictionary[String]] = js.native
  def options: js.UndefOr[ScenarioOptions] = js.native
}

// TODO: write ADT
sealed trait Scenario extends js.Object

object Scenario {

  @js.native
  trait SharedIterationsScenario extends BaseScenario with Scenario {
    def vus: js.UndefOr[Int] = js.native
    def iterations: js.UndefOr[Int] = js.native
    def maxDuration: js.UndefOr[String] = js.native
  }

  @js.native
  trait RampingVusScenario extends BaseScenario with Scenario {
    def stages: js.Array[Stage] = js.native
    def startVUs: js.UndefOr[Int] = js.native
    def gracefulRampDown: js.UndefOr[String] = js.native
  }
  object SharedIterationsScenario {
    def apply(
        startTime: Option[FiniteDuration] = None,
        gracefulStop: Option[FiniteDuration] = None,
        exec: Option[String] = None, // TODO: change to type
        env: Option[Map[String, String]] = None,
        tags: Option[Map[String, String]] = None,
        options: Option[ScenarioOptions] = None,
        vus: Option[Int] = None,
        iterations: Option[Int] = None,
        maxDuration: Option[FiniteDuration] = None
    ): Scenario =
      js.Dynamic
        .literal(
          executor = ExecutorOptionsType.SharedIterations,
          startTime = startTime.map(_.toK6).orUndefined,
          gracefulStop = gracefulStop.map(_.toK6).orUndefined,
          exec = exec.orUndefined,
          env = env.map(_.toJSDictionary).orUndefined,
          tags = tags.map(_.toJSDictionary).orUndefined,
          options = options.orUndefined,
          vus = vus.orUndefined,
          iterations = iterations.orUndefined,
          maxDuration = maxDuration.map(_.toK6).orUndefined
        )
        .asInstanceOf[Scenario]
  }

  object RampingVusScenario {
    def apply(
        startTime: Option[FiniteDuration] = None,
        gracefulStop: Option[FiniteDuration] = None,
        exec: Option[String] = None, // TODO: change to type
        env: Option[Map[String, String]] = None,
        tags: Option[Map[String, String]] = None,
        options: Option[ScenarioOptions] = None,
        stages: List[Stage] = Nil,
        startVUs: Option[Int] = None,
        gracefulRampDown: Option[FiniteDuration] = None
    ): Scenario =
      js.Dynamic
        .literal(
          executor = ExecutorOptionsType.RampingVus,
          startTime = startTime.map(_.toK6).orUndefined,
          gracefulStop = gracefulStop.map(_.toK6).orUndefined,
          exec = exec.orUndefined,
          env = env.map(_.toJSDictionary).orUndefined,
          tags = tags.map(_.toJSDictionary).orUndefined,
          options = options.orUndefined,
          stages = stages.toJSArray,
          startVUs = startVUs.orUndefined,
          gracefulRampDown = gracefulRampDown.map(_.toK6).orUndefined
        )
        .asInstanceOf[Scenario]
  }
}
