package com.sebarys.app.http.dto

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{ DefaultJsonProtocol, RootJsonFormat }

case class CreateUserDto(name: String, mail: String, age: Int)

object CreateUserDto extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val createUserJsonFormat: RootJsonFormat[CreateUserDto] = jsonFormat3(CreateUserDto.apply)
}
