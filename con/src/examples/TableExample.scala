package examples

import f.model.out.{Line, Table}
import modules.StaticAppModule.*

object TableExample:

  case class Person(name: String, age: Int)

  def people: Seq[Person] =
    Seq(
      Person("Kostas", 25),
      Person("George", 30),
      Person("Nick", 35)
    )

  def avgAge: Int = people.map(_.age).sum / people.size

@main
def calculateTable(): Int =
  runnerService.run { () =>
    Table.from(
      TableExample.people
    )
  }

@main
def avgAge(): Int =
  runnerService.run { () =>
    Line(
      "Average age = " + TableExample.avgAge
    )
  }
