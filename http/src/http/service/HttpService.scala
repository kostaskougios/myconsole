package http.service

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.client.RequestBuilding.Get
import akka.http.scaladsl.model.*
import akka.http.scaladsl.unmarshalling.{Unmarshal, Unmarshaller}
import akka.http.scaladsl.{Http, unmarshalling}
import modules.Beans

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}

class HttpService:
  private implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "SingleRequest")
  // needed for the future flatMap/onComplete in the end
  private implicit val executionContext: ExecutionContextExecutor = system.executionContext

  private val http = Http()

  def get(uri: String): Future[HttpResponse] = http.singleRequest(Get(uri = uri))
  def getAsString(uri: String): Future[String] = get(uri).flatMap(r => Unmarshaller.stringUnmarshaller(r.entity))

  def shutdown() =
    http.shutdownAllConnectionPools()
    system.terminate()

trait HttpServiceBeans extends Beans:
  lazy val httpService = registerShutDown(new HttpService) { _.shutdown() }
