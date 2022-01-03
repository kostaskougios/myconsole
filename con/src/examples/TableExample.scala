package examples

import f.model.out.{Line, Table}
import modules.StaticAppModule.*

object TableExample:

  case class Person(name: String, age: Int)

  def people =
    Seq(
      Person("Kostas", 25),
      Person("George", 30),
      Person("Nick", 35)
    )

  def avgAge = people.map(_.age).sum / people.size

@main
def calculateTable() =
  consoleRunnerService.run { () =>
    Table.from(
      TableExample.people
    )
  }

@main
def avgAge() =
  consoleRunnerService.run { () =>
    Line(
      "Average age = " + TableExample.avgAge
    )
  }
