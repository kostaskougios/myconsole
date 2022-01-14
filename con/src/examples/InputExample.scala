package examples

import f.model.in.YorNQuestion
import f.model.out.{Ask, Line}
import modules.StaticAppModule.*

object InputExample:
  def simpleYN =
    Ask(
      YorNQuestion("1st", "Are you over 30 years old?")
    ) { answer =>
      Line(s"Your answer : $answer")
    }

@main
def simpleYN =
  runnerService.run { () =>
    InputExample.simpleYN
  }
