package com.sebarys.app.user

import akka.Done
import com.sebarys.app.model.{User, UserId}
import com.sebarys.app.storage.UserRepository
import com.sebarys.app.validation.UserValidator

import scala.concurrent.{ExecutionContext, Future}

class UserService(userRepository: UserRepository, userValidator: UserValidator)(implicit ec: ExecutionContext) {

  def createUser(userToCreate: User): Future[UserId] = {
    userValidator.validate(userToCreate)
    userRepository.store(userToCreate)
  }

  def getUser(id: UserId): Future[User] = {
    userRepository.get(id)
      .flatMap {
        case Some(user) => Future.successful(user)
        case None => Future.failed(throw new NoSuchElementException(s"user with id: $id doesn't exist"))
      }
  }

  def deleteUser(id: UserId): Future[Done] = {
    userRepository.delete(id)
  }

  def getAllUsers(): Future[List[User]] = {
    userRepository.getAll()
  }
  
}
