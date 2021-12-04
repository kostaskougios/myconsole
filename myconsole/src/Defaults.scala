// https://dotty.epfl.ch/api/scala/sys/process.html
import modules.AppModule
import modules.AppModule.*
import utils.*

import scala.sys.process.*

def help =
  """
      |This is the help page for myconsole.
      |""".stripMargin

def ls =
  "ls".!!

@main
def googleQuery(q: String) =
  AppModule.withBeansDo { beans =>
    import beans.*
    println(httpService.getAsString(s"https://www.google.com/search?q=$q").await)
  }
