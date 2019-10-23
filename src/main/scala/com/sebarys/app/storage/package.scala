package com.sebarys.app

import com.sebarys.app.config.DatabaseConfig

import scala.concurrent.ExecutionContext

package object storage {
  class DBConnection(val executionContext: ExecutionContext)

  def initDatabaseConnection(config: DatabaseConfig)(implicit ec: ExecutionContext): DBConnection = {
    //TODO sth can go wrong here
    new DBConnection(ec)
  }

  def createUserRepository(dbConnection: DBConnection): UserRepository = {
    new UserRepository(dbConnection)
  }
}
