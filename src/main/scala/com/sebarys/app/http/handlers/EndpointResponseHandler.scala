package com.sebarys.app.http.handlers

import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.{ExceptionHandler, Route}

object EndpointResponseHandler {

  implicit def endpointExceptionHandler: ExceptionHandler =
    ExceptionHandler {
      case ex: IllegalArgumentException =>
        makeErrorResponse(StatusCodes.BadRequest, ex.getMessage)
      case ex: NoSuchElementException =>
        makeErrorResponse(StatusCodes.NotFound, ex.getMessage)
      case _: Exception =>
        makeErrorResponse(StatusCodes.InternalServerError, "Something went wrong please contact administrator")
    }


  private def makeErrorResponse(statusCode: StatusCode, message: String): Route = {

    val body = HttpEntity(
      contentType = ContentTypes.`application/json`,
      string =
        s"""{
        |  "status": ${statusCode.intValue()},
        |  "message": "$message"
        }""".stripMargin
    )

    complete(HttpResponse(statusCode, entity = body))
  }

}
