package com.sebarys.app.storage

import cats.effect.{Blocker, ContextShift, IO, Resource}
import com.sebarys.app.config.DatabaseConfig
import doobie.hikari.HikariTransactor
import doobie.util.ExecutionContexts
import org.flywaydb.core.Flyway

object Database {
  def transactor(config: DatabaseConfig)(implicit cs: ContextShift[IO]): Resource[IO, HikariTransactor[IO]] = {

    val transactorResource: Resource[IO, HikariTransactor[IO]] = for {
      connectEc <- ExecutionContexts.fixedThreadPool[IO](32) // our connect EC
      blockingEC <- Blocker[IO]    // our blocking EC
      hikariTransactor <- HikariTransactor.newHikariTransactor[IO](
        config.driver,                        // driver classname
        config.url,                           // connect URL
        config.user,                          // username
        config.password,                      // password
        connectEc,                            // await connection here
        blockingEC                            // execute JDBC operations here
      )
    } yield hikariTransactor
    transactorResource
  }

  def initialize(transactor: HikariTransactor[IO]): IO[Unit] = {
    transactor.configure { dataSource =>
      IO {
        val flyWay = Flyway.configure().dataSource(dataSource).load()
        flyWay.migrate()
        ()
      }
    }
  }
}
