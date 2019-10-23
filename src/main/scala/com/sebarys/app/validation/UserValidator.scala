package com.sebarys.app.validation

import java.util.regex.Pattern

import com.sebarys.app.model.User

class UserValidator {

  private val emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&*+/=?{}~^.-]+@[a-zA-Z0-9.-]+$")

  private def nameNotEmpty(name: String) = !name.isEmpty
  private def emailMatchesPattern(email: String) = email != null && emailPattern.matcher(email).matches()
  private def emailNotEmpty(email: String) = !email.isEmpty
  private def ageInExpectedRange(age: Int) = age > 0 && age < 150

  private def checkRule(rule: Boolean, errorDetailMessage: String): Unit = {
    if (!rule) throw new IllegalArgumentException(s"Received invalid argument, details: $errorDetailMessage")
  }

  //TODO sth can go wrong here
  def validate(userToCreate: User): Unit = {
    checkRule(nameNotEmpty(userToCreate.name), "new user name couldn't be empty")
    checkRule(emailMatchesPattern(userToCreate.mail), "new user mail is not in correct")
    checkRule(emailNotEmpty(userToCreate.mail), "new user mail couldn't be empty")
    checkRule(ageInExpectedRange(userToCreate.age), "new user age has invalid value")
  }

}
