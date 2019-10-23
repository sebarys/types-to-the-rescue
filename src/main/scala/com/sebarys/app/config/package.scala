package com.sebarys.app

import com.typesafe.config.ConfigFactory

package object config {

  case class ServerConfig(host: String, port: Int)

  case class DatabaseConfig(driver: String, url: String, user: String, password: String)

  case class Config(server: ServerConfig, database: DatabaseConfig)

  object Config {

    import pureconfig._
    import pureconfig.generic.auto._

    //TODO sth can go wrong here
    def load(configFile: String = "application.conf"): Config = {
      ConfigSource.fromConfig(ConfigFactory.load(configFile)).load[Config]
        .getOrElse(throw new RuntimeException("Error during parsing configuration"))
    }
  }

}
