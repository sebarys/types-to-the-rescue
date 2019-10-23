package com.sebarys.app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.sebarys.app.config.Config

import scala.concurrent.{ExecutionContext, Future}
import scala.io.StdIn

object Application extends App {

  implicit val system: ActorSystem = ActorSystem("actorSystemForAkkaHttp")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher
  
  val serverBindingF: Future[Http.ServerBinding] = for {
    config <- Future.fromTry(Config.load())
    dbConnection <- storage.initDatabaseConnection(config.database)
    userRepository = storage.createUserRepository(dbConnection)
    userValidator = validation.createUserValidator()
    userService = user.createService(userRepository, userValidator)
    userEndpoints = http.createUserEndpoints(userService)
    serverBindingFuture <- http.startServer(config.server, userEndpoints)
  } yield serverBindingFuture
  
  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  serverBindingF.onComplete(_ => system.terminate())
}
