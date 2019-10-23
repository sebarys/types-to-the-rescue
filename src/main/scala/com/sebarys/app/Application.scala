package com.sebarys.app

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.sebarys.app.config.Config

import scala.concurrent.ExecutionContext
import scala.io.StdIn

object Application extends App {

  implicit val system: ActorSystem = ActorSystem("actorSystemForAkkaHttp")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher

  val config = Config.load()
  val dbConnection = storage.initDatabaseConnection(config.database)
  val userRepository = storage.createUserRepository(dbConnection)
  val userValidator = validation.createUserValidator()
  val userService = user.createService(userRepository, userValidator)

  val userEndpoints = http.createUserEndpoints(userService)

  val serverBindingFuture = http.startServer(config.server, userEndpoints)
  
  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  serverBindingFuture.onComplete(_ => system.terminate())
}
