package examples

import f.model.out.Table
import modules.StaticAppModule.*

object TableExample:
  def calculateTable =
    Seq(
      Seq("name", "age"),
      Seq("Kostas", 25),
      Seq("George", 30),
      Seq("Nick", 35)
    )

@main
def calculateTable() =
  consoleRunnerService.run { () =>
    Table(TableExample.calculateTable)
  }
