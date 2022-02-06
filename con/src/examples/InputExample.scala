package examples

import f.model.in.{Input, YorNQuestion}
import f.model.out.Line
import f.model.{Answers, InOut}
import modules.StaticAppModule.*

object InputExample:
  val Q30YearsOld = YorNQuestion("1st", "Are you over 30 years old?")

  case class State(over30: Option[Boolean] = None)

import examples.InputExample.*
@main
def simpleYNMain =
  val rs = runnerService[State]
  rs.calcNewState(State(), Answers.empty) { (state, input, answer) =>
    input match
      case Q30YearsOld => state.copy(over30 = Some(Q30YearsOld.isYes(answer)))
  }
