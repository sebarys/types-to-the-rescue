package com.sebarys.app

package object validation {

  def createUserValidator(): UserValidator = {
    new UserValidator
  }
}
