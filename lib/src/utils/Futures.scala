package utils

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

extension [R](f: Future[R]) def await = Await.result(f, Duration.Inf)
