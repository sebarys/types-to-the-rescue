package com.sebarys.app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.sebarys.app.config.ServerConfig
import com.sebarys.app.http.endpoints.UserRoutes
import com.sebarys.app.user.UserService

import scala.concurrent.Future

package object http {

  def createUserEndpoints(userService: UserService)(implicit ec: ActorSystem): Route = {
    UserRoutes.createUserRoutes(userService)
  }

  def startServer(config: ServerConfig, userRoute: Route)(implicit ec: ActorSystem, am: ActorMaterializer): Future[Http.ServerBinding] = {
    import com.sebarys.app.http.handlers.EndpointResponseHandler.endpointExceptionHandler

    Http().bindAndHandle(userRoute, config.host, config.port)
  }
}
