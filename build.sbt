name := """gdeed"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, SbtWeb)

scalaVersion := "2.11.8"

routesGenerator := InjectedRoutesGenerator

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalaz" %% "scalaz-core" % "7.2.2",
  "org.reactivemongo" %% "play2-reactivemongo" % "0.11.14",
  "org.reactivemongo" %% "reactivemongo-akkastream" % "0.12.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test,
  "org.mindrot" % "jbcrypt" % "0.3m",
  "org.webjars" %% "webjars-play" % "2.5.0",
  "org.webjars.bower" % "react" % "15.3.2",
  "org.webjars" % "marked" % "0.3.2-1",
  "org.webjars" % "jquery" % "3.1.1",
  "org.webjars" % "jquery-ui" % "1.12.1",
  "org.webjars" % "react-grid-layout" % "0.9.2",
  "org.webjars" % "jqgrid" % "4.7.0"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

scalacOptions in ThisBuild ++= Seq("-feature", "-language:postfixOps")