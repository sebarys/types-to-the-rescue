package com.sebarys.app.http.dto

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.sebarys.app.model.User
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

case class UserDto(userId: String, name: String, mail: String, age: Int)

object UserDto extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val userJsonFormat: RootJsonFormat[UserDto] = jsonFormat4(UserDto.apply)

  def fromUser(user: User): UserDto = {
    UserDto(user.id.value, user.name.value, user.mail.value, user.age.value)
  }
}
