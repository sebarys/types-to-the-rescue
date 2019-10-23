package com.sebarys.app.validation

import java.util.regex.Pattern

import com.sebarys.app.model.{Age, Mail, User, Username}

import scala.util.Try

class UserValidator {

  private val emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&*+/=?{}~^.-]+@[a-zA-Z0-9.-]+$")

  private def nameNotEmpty(name: Username) = !name.value.isEmpty
  private def emailMatchesPattern(email: Mail) = email != null && emailPattern.matcher(email.value).matches()
  private def emailNotEmpty(email: Mail) = !email.value.isEmpty
  private def ageInExpectedRange(age: Age) = age.value > 0 && age.value < 150

  private def checkRule(rule: Boolean, errorDetailMessage: String): Try[Unit] = {
    Try {
      if (!rule) throw new IllegalArgumentException(s"Received invalid argument, details: $errorDetailMessage")
    }
  }
  
  def validate(userToCreate: User): Try[Unit] = {
    for {
     _ <- checkRule(nameNotEmpty(userToCreate.name), "new user name couldn't be empty")
     _ <- checkRule(emailMatchesPattern(userToCreate.mail), "new user mail is not in correct")
     _ <- checkRule(emailNotEmpty(userToCreate.mail), "new user mail couldn't be empty")
     _ <- checkRule(ageInExpectedRange(userToCreate.age), "new user age has invalid value")
    } yield ()
  }

}
