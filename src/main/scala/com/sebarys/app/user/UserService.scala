package com.sebarys.app.user

import akka.Done
import com.sebarys.app.model.{User, UserId}
import com.sebarys.app.storage.UserRepository
import com.sebarys.app.validation.UserValidator

import scala.concurrent.{ExecutionContext, Future}

class UserService(userRepository: UserRepository, userValidator: UserValidator)(implicit ec: ExecutionContext) {

  def createUser(userToCreate: User): Future[UserId] = {
    for {
      _ <- Future.fromTry(userValidator.validate(userToCreate))
      userId <- userRepository.store(userToCreate)
    } yield userId
  }

  def getUser(id: UserId): Future[User] = {
    for {
      maybeUser <- userRepository.get(id)
    } yield maybeUser match {
      case Some(user) => user
      case None => throw new NoSuchElementException(s"user with id: $id doesn't exist")
    }
  }

  def deleteUser(id: UserId): Future[Done] = {
    userRepository.delete(id)
  }

  def getAllUsers(): Future[List[User]] = {
    userRepository.getAll()
  }
  
}
