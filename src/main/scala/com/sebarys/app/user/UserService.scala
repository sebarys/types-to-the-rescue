package com.sebarys.app.user

import akka.Done
import com.sebarys.app.http.dto.CreateUserDto
import com.sebarys.app.storage.UserRepository
import com.sebarys.app.model.User
import com.sebarys.app.validation.UserValidator

import scala.concurrent.{ExecutionContext, Future}

class UserService(userRepository: UserRepository, userValidator: UserValidator)(implicit ec: ExecutionContext) {

  def createUser(createUserDto: CreateUserDto): Future[String] = {
    val userToCreate = User.fromCreateUserDto(createUserDto)
    userValidator.validate(userToCreate)
    userRepository.store(userToCreate)
  }

  def getUser(id: String): Future[User] = {
    userRepository.get(id)
      .flatMap {
        case Some(user) => Future.successful(user)
        case None => Future.failed(throw new NoSuchElementException(s"user with id: $id doesn't exist"))
      }
  }

  def deleteUser(id: String): Future[Done] = {
    userRepository.delete(id)
  }

  def getAllUsers(): Future[List[User]] = {
    userRepository.getAll()
  }
  
}
