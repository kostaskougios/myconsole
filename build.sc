import Deps._
import com.google.common.base.CaseFormat
import mill._
import mill.scalalib._
import org.apache.commons.lang3.StringUtils
import os.{Path, PermSet}

object con extends Common with AssemblyMultipleApps {
  override def moduleDeps = Seq(http, `f-model`, `f-service`)
}

object lib extends Common {}

object `f-model` extends Common {}

object `f-service` extends Common {
  override def moduleDeps = Seq(`f-model`)
}

object http extends Common {
  override def ivyDeps = Agg(Akka: _*)
  override def moduleDeps = Seq(lib)
}

trait Common extends ScalaModule {
  override def scalaVersion = ScalaVersion
  override def scalacOptions = Seq("-deprecation", "-feature", "-unchecked")
}

trait AssemblyMultipleApps extends JavaModule {
  def assemblyMultipleApps = T {
    val assemblyPath = assembly()
    val compilationResult = compile()
    val mains = zincWorker.worker().discoverMainClasses(compilationResult)

    println(s"Found these executable main methods:\n${mains.mkString("\n")}")
    val targetPath = assemblyPath.path
    val targetDir = Path(targetPath.toNIO.getParent)
    val permSet = PermSet.fromString("rwx------")
    for (m <- mains) {
      val script =
        s"""
           |
           |realpath() {
           |    [[ $$1 = /* ]] && echo "$$1" || echo "$$PWD/$${1#./}"
           |}
           |temp=$$( realpath "$$0" )
           |S=$$(dirname "$$temp")
           |
           |java -cp $$S/out.jar $m "$$@"
           |""".stripMargin
      val scriptName= if(m.contains(".")) StringUtils.substringAfterLast(m,".") else m
      val targetScript = targetDir / CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, scriptName)
      os.write(targetScript, script, permSet)
    }
    println(s"executable scripts under $targetDir")
  }
}

object Deps {
  val ScalaVersion = "3.1.1"
  val ScalaTest = ivy"org.scalatest::scalatest:3.2.10"

  val AkkaVersion = "2.6.17"
  val AkkaHttpVersion = "10.2.7"

  val Akka = Seq(
    ivy"com.typesafe.akka::akka-actor-typed:$AkkaVersion".withDottyCompat(ScalaVersion),
    ivy"com.typesafe.akka::akka-stream:$AkkaVersion".withDottyCompat(ScalaVersion),
    ivy"com.typesafe.akka::akka-http:$AkkaHttpVersion".withDottyCompat(ScalaVersion)
  )
}
