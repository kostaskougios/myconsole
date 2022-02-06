package f.service

import f.model.in.{Input, YorNQuestion}
import f.model.out.{Line, Output, Table}
import f.service.utils.TableFormatter

import scala.io.StdIn

class ConsoleRenderer:
  def render(output: Output): Unit =
    val text = output match
      case Line(line)   => line
      case Table(table) => TableFormatter.toConsole(table)

    println(text)

  def ask(input: Input): String =
    input match
      case q @ YorNQuestion(id, question, yesText, noText) =>
        println(s"$question ($yesText / $noText) ?")
        StdIn.readLine()
