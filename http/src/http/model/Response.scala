package http.model

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.unmarshalling.Unmarshaller

import scala.concurrent.{ExecutionContextExecutor, Future}

class Response(r:HttpResponse)(implicit system: ActorSystem[Nothing], executionContext: ExecutionContextExecutor) :
  def entityToString: Future[String] = Unmarshaller.stringUnmarshaller(r.entity)
