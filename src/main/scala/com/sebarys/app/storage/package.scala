package com.sebarys.app

import com.sebarys.app.config.DatabaseConfig

import scala.concurrent.{ExecutionContext, Future}

package object storage {
  class DBConnection(val executionContext: ExecutionContext)

  def initDatabaseConnection(config: DatabaseConfig)(implicit ec: ExecutionContext): Future[DBConnection] = {
    Future {
      new DBConnection(ec)
    }
  }

  def createUserRepository(dbConnection: DBConnection): UserRepository = {
    new UserRepository(dbConnection)
  }
}
