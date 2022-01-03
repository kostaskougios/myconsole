package f.service
import f.model.out.*
import f.service.utils.TableFormatter

class ConsoleRunnerService:
  def run(f: () => Output): Unit =
    val o = f()
    val c = o match
      case Line(line)   => line
      case Table(table) => TableFormatter.toConsole(table)

    println(c)

trait ConsoleRunnerServiceBeans:
  lazy val consoleRunnerService = new ConsoleRunnerService
