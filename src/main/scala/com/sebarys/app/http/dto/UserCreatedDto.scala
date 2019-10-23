package com.sebarys.app.http.dto

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

case class UserCreatedDto(id: String)

object UserCreatedDto extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val userCreatedJsonFormat: RootJsonFormat[UserCreatedDto] = jsonFormat1(UserCreatedDto.apply)

  def fromUserId(userId: String): UserCreatedDto = UserCreatedDto(userId)
}
