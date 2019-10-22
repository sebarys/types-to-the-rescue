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
package com.sebarys.app.http.dto

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{ DefaultJsonProtocol, RootJsonFormat }

case class CreateUserDto(name: String, mail: String, age: Int)

object CreateUserDto extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val createUserJsonFormat: RootJsonFormat[CreateUserDto] = jsonFormat3(CreateUserDto.apply)
}
