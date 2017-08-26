name := "basic-project"

organization := "example"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.2"

crossScalaVersions := Seq("2.10.4", "2.11.2")

resolvers += Resolver.sonatypeRepo("releases")

val circeVersion = "0.8.0"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.12" % "3.0.4" % "test",
  "org.scalacheck" % "scalacheck_2.12" % "1.13.5" % "test",
  "com.danielasfregola" %% "twitter4s" % "5.1",
  "com.typesafe.play" %% "play-ahc-ws-standalone" % "1.0.4",
  "org.scalactic" % "scalactic_2.12" % "3.0.4",
  "com.typesafe" % "config" % "1.3.1",
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion)

initialCommands := "import example._"
