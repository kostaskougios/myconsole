package f.service
import f.model.out.*
import f.service.utils.TableFormatter

/** Runs the code.
  *   - If the code runs within a terminal, it uses a terminal in/out.
  *   - If the code runs in the notebook env, it uses notebook in/out
  */
class RunnerService:
  def run(f: () => Output): Int =
    val o = f()
    val c = o match
      case Line(line)   => line
      case Table(table) => TableFormatter.toConsole(table)
    println(c)
    0

trait ConsoleRunnerServiceBeans:
  lazy val runnerService = new RunnerService
