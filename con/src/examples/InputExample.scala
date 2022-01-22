package examples

import f.model.InOut
import f.model.in.{Input, YorNQuestion}
import f.model.out.Line
import modules.StaticAppModule.*

object InputExample:
  private val Q30YearsOld = YorNQuestion("1st", "Are you over 30 years old?")

  case class State(over30: Option[Boolean] = None)

  val inOut = InOut.forState[State]

  def simpleYN(state: State): InOut[State] =
    if state.over30.isEmpty then
      inOut.withInput(Q30YearsOld) { (s, answer) =>
        s.copy(over30 = Some(answer == "Y"))
      }
    else inOut

import examples.InputExample.*
@main
def simpleYNMain =
  val newS = runnerService[State].execute(
    State(),
    simpleYN
  )
  println(newS)
