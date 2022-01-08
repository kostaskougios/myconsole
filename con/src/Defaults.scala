// https://dotty.epfl.ch/api/scala/sys/process.html
import modules.StaticAppModule.*
import utils.*

import scala.sys.process.*

@main
def help =
  println("""
      |This is the help page for myconsole.
      |""".stripMargin)

def ls =
  "ls".!!.split("\n").toList

@main
def echoArgs(arg1: String, arg2: String) = println(arg1 + ", " + arg2)

@main
def googleQuery(q: String) =
  println(httpService.get(s"https://www.google.com/search?q=$q").entityToString)
