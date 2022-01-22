package f.service
import f.model.InOut
import f.model.in.*
import f.model.out.*
import f.service.utils.TableFormatter

import scala.io.StdIn

/** Runs the code.
  *   - If the code runs within a terminal, it uses a terminal in/out.
  *   - If the code runs in the notebook env, it uses notebook in/out
  */
class RunnerService[S <: InputState[S]]:
  def execute(s: S, inOut: S => InOut): S =
    val newS = inOut(s).io.foldLeft(s) {
      case (s, i: Input) => ask(s, i)
      case (s, o: Output) =>
        render(o)
        s
    }
    newS

  def render(output: Output): Unit =
    val text = output match
      case Line(line)   => line
      case Table(table) => TableFormatter.toConsole(table)
    println(text)

  def ask(s: S, input: Input): S =
    input match
      case q @ YorNQuestion(id, question, yesText, noText) =>
        println(s"$question ($yesText / $noText) ?")
        val in = StdIn.readLine()
        if in.equalsIgnoreCase(yesText) then s.withAnswer(q, yesText)
        else if in.equalsIgnoreCase(noText) then s.withAnswer(q, noText)
        else s

trait RunnerServiceBeans:
  def runnerService[S <: InputState[S]] = new RunnerService[S]
