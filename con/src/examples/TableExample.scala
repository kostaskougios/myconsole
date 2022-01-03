package examples

import f.model.out.Table
import modules.StaticAppModule.*

object TableExample:
  def people =
    Seq(
      Person("Kostas", 25),
      Person("George", 30),
      Person("Nick", 35)
    )

  def avgAge = people.map(_.age).sum

@main
def calculateTable() =
  consoleRunnerService.run { () =>
    Table.from(
      TableExample.people
    )
  }

case class Person(name: String, age: Int):
  def toRow = Seq(name, age)
