package examples

import f.model.InOut
import f.model.in.{Input, InputState, YorNQuestion}
import f.model.out.Line
import modules.StaticAppModule.*

object InputExample:
  private val Q30YearsOld = YorNQuestion("1st", "Are you over 30 years old?")

  case class State(over30: Option[Boolean] = None) extends InputState[State]:
    override def withAnswer(forInput: Input, answer: String): State =
      forInput match
        case Q30YearsOld => copy(over30 = Some(answer == "Y"))

  def simpleYN(state: State) =
    if state.over30.isEmpty then InOut(Q30YearsOld) else InOut.Done

import examples.InputExample.*
@main
def simpleYNMain =
  val newS = runnerService[State].execute(
    State(),
    simpleYN
  )
  println(newS)
