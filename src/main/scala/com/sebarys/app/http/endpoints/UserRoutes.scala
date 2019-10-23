package com.sebarys.app.http.endpoints

import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.{delete, get, post}
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import com.sebarys.app.http.dto.{CreateUserDto, UserCreatedDto, UserDto}
import com.sebarys.app.user.UserService
import spray.json.DefaultJsonProtocol

import scala.concurrent.ExecutionContextExecutor

object UserRoutes extends SprayJsonSupport with DefaultJsonProtocol {

  def createUserRoutes(userService: UserService)(implicit actorSystem: ActorSystem): Route = {
    implicit val ec: ExecutionContextExecutor = actorSystem.dispatcher

    pathPrefix("users") {
      pathEnd {
        get {
          val userDtoListF = userService.getAllUsers().map(userList => userList.map(UserDto.fromUser))
          complete(userDtoListF)
        } ~
          post {
            entity(as[CreateUserDto]) { userToCreate =>
              import spray.json._
              val userCreationF = userService.createUser(userToCreate)
                .map(userId => HttpResponse(StatusCodes.Created, entity = UserCreatedDto.fromUserId(userId).toJson.toString))
              complete(userCreationF)
            }
          }
      } ~
        path(Segment) { userId =>
          get {
            val userF = userService.getUser(userId).map(UserDto.fromUser)
            complete(userF)
          } ~
            delete {
              val userDeletionF = userService.deleteUser(userId).map(_ => HttpResponse(StatusCodes.NoContent))
              complete(userDeletionF)
            }
        }
    }
  }
}
