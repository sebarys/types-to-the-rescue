package com.sebarys.app.storage

import akka.Done
import com.sebarys.app.model.{User, UserId}

import scala.concurrent.{ExecutionContext, Future}

class UserRepository(dbConnection: DBConnection) {

  implicit val ec: ExecutionContext = dbConnection.executionContext

  var inMemoryStore = Map.empty[UserId, User]

  def store(user: User): Future[UserId] = {
    Future{
      val userId = user.id
      Thread.sleep(1000)
      inMemoryStore = inMemoryStore + (userId -> user)
      userId
    }
  }

  def get(id: UserId): Future[Option[User]] = {
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

  def delete(id: UserId): Future[Done] = {
    Future{
      Thread.sleep(100)
      inMemoryStore = inMemoryStore - id
      Done
    }
  }
}
