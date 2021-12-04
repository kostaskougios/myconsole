import Deps._
import mill._
import mill.scalalib._

object myconsole extends Common {
  def scalaVersion = ScalaVersion
  override def ivyDeps = Agg(Akka: _*)

//  object test extends Tests
//  {
//    override def ivyDeps =  Agg(ScalaTest)
//    override def testFramework = T("org.scalatest.tools.Framework")
//  }
}

trait Common extends ScalaModule {
  override def scalaVersion = "3.1.0"
  override def scalacOptions = Seq("-deprecation", "-feature", "-unchecked")
}

object Deps {
  val ScalaVersion = "3.1.0"
  val ScalaTest = ivy"org.scalatest::scalatest:3.2.10"

  val AkkaVersion = "2.6.17"
  val AkkaHttpVersion = "10.2.7"

  val Akka = Seq(
    ivy"com.typesafe.akka::akka-actor-typed:$AkkaVersion".withDottyCompat(ScalaVersion),
    ivy"com.typesafe.akka::akka-stream:$AkkaVersion".withDottyCompat(ScalaVersion),
    ivy"com.typesafe.akka::akka-http:$AkkaHttpVersion".withDottyCompat(ScalaVersion)
  )
}
