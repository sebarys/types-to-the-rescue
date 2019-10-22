package com.sebarys.app.http.endpoints

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.model.{ ContentTypes, HttpEntity, HttpResponse, StatusCodes }
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.{ delete, get, post }
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import com.sebarys.app.user.User
import com.sebarys.app.http.dto.{ CreateUserDto, UserDto }

trait UserRoutes {

  // we leave these abstract, since they will be provided by the App
  implicit def system: ActorSystem

  lazy val log = Logging(system, classOf[UserRoutes])

  lazy val userRoutes: Route =
    pathPrefix("users") {
      pathEnd {
        get {
          // get all users
          log.info("Getting all users")
          import spray.json._
          val entity = HttpEntity(
            contentType = ContentTypes.`application/json`,
            string = List(User("user1", "Max", "max@example.com", 30)).map(UserDto.fromUser).toJson.compactPrint)
          complete(HttpResponse(StatusCodes.OK, entity = entity))
        } ~ post {
          entity(as[CreateUserDto]) { userToCreate =>
            // create user
            log.info("Creating user {}", userToCreate)
            complete(HttpResponse(StatusCodes.OK))
          }
        }
      } ~ path(Segment) { userId =>
        get {
          // get user with given userId
          log.info("Getting user with id {}", userId)
          complete(HttpResponse(StatusCodes.OK))
        } ~ delete {
          // delete user with given userId
          log.info("Deleting user with id {}", userId)
          complete(HttpResponse(StatusCodes.OK))
        }
      }
    }
}
