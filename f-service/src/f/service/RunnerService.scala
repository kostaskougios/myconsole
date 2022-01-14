package f.service
import f.model.in.*
import f.model.out.*
import f.service.utils.TableFormatter

import scala.io.StdIn

/** Runs the code.
  *   - If the code runs within a terminal, it uses a terminal in/out.
  *   - If the code runs in the notebook env, it uses notebook in/out
  */
class RunnerService:
  def run(f: () => Output): Int = run(Map.empty, f)

  private def run(answers: Map[String, String], f: () => Output): Int =
    val o = f()
    val c = o match
      case Line(line)   => line
      case Table(table) => TableFormatter.toConsole(table)
      case Ask(input: Input) =>
        ask(answers, f, input)
    println(c)
    0

  private def ask(answers: Map[String, String], f: () => Output, input: Input) =
    val (id, in) = input match
      case YorNQuestion(id, question, yesText, noText) =>
        println(s"$question ($yesText / $noText) ?")
        val in = StdIn.readLine()
        (id, in)

    run(answers + (id -> in), f)

trait ConsoleRunnerServiceBeans:
  lazy val runnerService = new RunnerService
