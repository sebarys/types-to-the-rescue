/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2019 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 */
package com.sebarys.app

import java.util.UUID

import com.sebarys.app.http.dto.{CreateUserDto, UserDto}

package object model {
  case class UserId(value: String) extends AnyVal
  case class Username(value: String) extends AnyVal
  case class Mail(value: String) extends AnyVal
  case class Age(value: Int) extends AnyVal

  case class User(id: UserId, name: Username, mail: Mail, age: Age)

  object User {
    def fromUserDto(userDto: UserDto): User = {
      User(UserId(userDto.userId), Username(userDto.name), Mail(userDto.mail), Age(userDto.age))
    }

    def fromCreateUserDto(createUserDto: CreateUserDto): User = {
      User(UserId(UUID.randomUUID().toString), Username(createUserDto.name), Mail(createUserDto.mail), Age(createUserDto.age))
    }
  }

}
