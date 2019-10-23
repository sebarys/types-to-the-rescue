package com.sebarys.app

import com.sebarys.app.storage.UserRepository
import com.sebarys.app.validation.UserValidator

import scala.concurrent.ExecutionContext

package object user {

  def createService(userRepository: UserRepository, userValidator: UserValidator)(implicit ec: ExecutionContext): UserService = {
    new UserService(userRepository, userValidator)
  }

}
