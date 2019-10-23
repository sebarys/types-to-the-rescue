lazy val AkkaHttpVersion    = "10.1.9"
lazy val AkkaVersion        = "2.5.25"
lazy val PureConfigVersion  = "0.12.1"
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

      // Test
      "com.typesafe.akka" %% "akka-http-testkit"    % AkkaHttpVersion   % Test,
      "com.typesafe.akka" %% "akka-testkit"         % AkkaVersion       % Test,
      "com.typesafe.akka" %% "akka-stream-testkit"  % AkkaVersion       % Test,
      "org.scalatest"     %% "scalatest"            % ScalaTestVersion  % Test
    )
  )
