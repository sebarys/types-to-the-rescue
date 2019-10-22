package com.sebarys.app.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.sebarys.app.config.ServerConfig
import com.sebarys.app.http.endpoints.UserRoutes

import scala.concurrent.{ExecutionContext, Future}

object HttpServer extends UserRoutes {
  implicit val system: ActorSystem = ActorSystem("helloAkkaHttpServer")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher
  lazy val routes: Route = userRoutes


  def startServer(config: ServerConfig): Future[Http.ServerBinding] = {
    Http().bindAndHandle(routes, config.host, config.port)
  }
}
