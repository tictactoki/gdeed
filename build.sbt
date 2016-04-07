name := """gdeed"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, SbtWeb)

scalaVersion := "2.11.7"

routesGenerator := InjectedRoutesGenerator

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.reactivemongo" %% "play2-reactivemongo" % "0.11.11",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test,
  "org.mindrot" % "jbcrypt" % "0.3m",
  "org.webjars" %% "webjars-play" % "2.4.0-1",
  "org.webjars" % "react" % "0.14.7",
  "org.webjars" % "jquery" % "2.1.4",
  "org.webjars.bower" % "marked" % "0.3.5",
  "org.webjars.bower" % "react-bootstrap" % "0.28.3",
  "org.webjars.bower" % "griddle-react" % "0.2.0"


)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

scalacOptions in ThisBuild ++= Seq("-feature", "-language:postfixOps")
