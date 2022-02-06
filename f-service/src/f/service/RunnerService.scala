package f.service
import f.model.in.*
import f.model.out.*
import f.model.{Answers, InOut}
import f.service.utils.TableFormatter

import scala.io.StdIn

/** Runs the code.
  *   - If the code runs within a terminal, it uses a terminal in/out.
  *   - If the code runs in the notebook env, it uses notebook in/out
  */
class RunnerService[S]:
  def execute(s: S, answers: Answers, inOut: S => InOut[S]): S =
    val io = inOut(s)

trait RunnerServiceBeans:
  def runnerService[S] = new RunnerService[S]
