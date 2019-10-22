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
package com.sebarys.app.user

import java.util.UUID

import com.sebarys.app.http.dto.{ CreateUserDto, UserDto }

case class User(id: String, name: String, mail: String, age: Int)

object User {
  def fromUserDto(userDto: UserDto): User = {
    User(userDto.userId, userDto.name, userDto.mail, userDto.age)
  }

  def fromCreateUserDto(createUserDto: CreateUserDto): User = {
    User(UUID.randomUUID().toString, createUserDto.name, createUserDto.mail, createUserDto.age)
  }
}