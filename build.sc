import Deps._
import mill._
import mill.scalalib._
import os.{Path, PermSet}

object con extends Common {
  override def scalaVersion = ScalaVersion
  override def ivyDeps = Agg(Akka: _*)

  def assemblyMultipleApps = T {
    val assemblyPath = assembly()
    val mains = zincWorker.worker().discoverMainClasses(compile())

    println(s"Found these executable main methods:\n${mains.mkString("\n")}")
    val targetPath = assemblyPath.path
    val targetDir = Path(targetPath.toNIO.getParent)
    val permSet = PermSet.fromString("rwx------")
    for (m <- mains) {
      val script =
        s"""
           |temp=$$( realpath "$$0" )
           |S=$$(dirname "$$temp")
           |
           |java -cp $$S/out.jar $m "$$@"
           |""".stripMargin
      val targetScript = targetDir / m
      os.write(targetScript, script, permSet)
    }
    println(s"executable scripts under $targetDir")
  }

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
