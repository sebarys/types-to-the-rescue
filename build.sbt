lazy val AkkaHttpVersion    = "10.1.9"
lazy val AkkaVersion        = "2.5.25"
lazy val PureConfigVersion  = "0.12.1"
lazy val DoobieVersion      = "0.8.4"
lazy val H2Version          = "1.4.199"
lazy val FlywayVersion      = "6.0.4"
lazy val ScalaTestVersion   = "3.0.7"

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization    := "com.sebarys",
      scalaVersion    := "2.12.8",
      scalacOptions   += "-Ypartial-unification"
    )),
    name := "types-to-the-rescue",
    libraryDependencies ++= Seq(
      // HTTP
      "com.typesafe.akka" %% "akka-http"            % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml"        % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"          % AkkaVersion,

      // config
      "com.github.pureconfig" %% "pureconfig"       % PureConfigVersion,

      // DB
      "com.h2database"    %  "h2"                   % H2Version,
      "org.flywaydb"      %  "flyway-core"          % FlywayVersion,
      "org.tpolecat"      %% "doobie-core"          % DoobieVersion,
      "org.tpolecat"      %% "doobie-hikari"        % DoobieVersion,
      "org.tpolecat"      %% "doobie-h2"            % DoobieVersion,

      // Test
      "com.typesafe.akka" %% "akka-http-testkit"    % AkkaHttpVersion   % Test,
      "com.typesafe.akka" %% "akka-testkit"         % AkkaVersion       % Test,
      "com.typesafe.akka" %% "akka-stream-testkit"  % AkkaVersion       % Test,
      "org.scalatest"     %% "scalatest"            % ScalaTestVersion  % Test
    )
  )
