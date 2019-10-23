package com.sebarys.app

import com.typesafe.config.ConfigFactory

import scala.util.Try

package object config {

  case class ServerConfig(host: String, port: Int)

  case class DatabaseConfig(driver: String, url: String, user: String, password: String)

  case class Config(server: ServerConfig, database: DatabaseConfig)

  object Config {

    import pureconfig._
    import pureconfig.generic.auto._

    def load(configFile: String = "application.conf"): Try[Config] = {
       Try{
         ConfigSource.fromConfig(ConfigFactory.load(configFile)).loadOrThrow[Config]
       }
    }
  }

}
