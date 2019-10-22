package com.sebarys.app

import cats.effect.{ContextShift, IO}
import com.sebarys.app.config.Config
import com.sebarys.app.http.HttpServer
import com.sebarys.app.http.HttpServer.system
import com.sebarys.app.storage.Database

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}

object Application extends App {

  implicit val executionContext = ExecutionContext.global
  implicit val cs: ContextShift[IO] = IO.contextShift(executionContext)
  val program: IO[Unit] = for {
    config <- Config.load()
    _ <- Database.transactor(config.database).use(transactor => {
      for {
        _ <- Database.initialize(transactor)
        eitherServerBinding <- IO.fromFuture(IO { HttpServer.startServer(config.server) }).attempt
        _ <- eitherServerBinding match {
          case Left(ex) => IO {
            Console.err.println(s"Server could not start!")
            ex.printStackTrace()
            system.terminate()
          }
          case Right(bound) => IO {
            println(s"Server online at http://${bound.localAddress.getHostString}:${bound.localAddress.getPort}/")
          }
        }
        _ <- IO { Await.result(system.whenTerminated, Duration.Inf) }
      } yield ()
    })
  } yield ()
  
  program.unsafeRunSync()
}
