package com.sebarys.app.storage

import akka.Done
import com.sebarys.app.model.User

import scala.concurrent.{ExecutionContext, Future}

class UserRepository(dbConnection: DBConnection) {

  implicit val ec: ExecutionContext = dbConnection.executionContext

  var inMemoryStore = Map.empty[String, User]

  def store(user: User): Future[String] = {
    Future{
      val userId = user.id
      Thread.sleep(1000)
      inMemoryStore = inMemoryStore + (userId -> user)
      userId
    }
  }

  def get(id: String): Future[Option[User]] = {
    Future{
      Thread.sleep(200)
      inMemoryStore.get(id)
    }
  }

  def getAll(): Future[List[User]] = {
    Future{
      Thread.sleep(2000)
      inMemoryStore.values.toList
    }
  }

  def delete(id: String): Future[Done] = {
    Future{
      Thread.sleep(100)
      inMemoryStore = inMemoryStore - id
      Done
    }
  }
}
