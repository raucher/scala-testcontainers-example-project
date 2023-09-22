ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.8"

val testcontainersScalaVersion = "0.41.0"
//val testcontainersScalaVersion = "0.40.12"

lazy val root = (project in file("."))
  .settings(
    name := "scala-testcontainers-example-project"
  )

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.17" % Test,
  "ch.qos.logback" % "logback-classic" % "1.3.10" % Test,
  "com.dimafeng" %% "testcontainers-scala-scalatest" % testcontainersScalaVersion % Test,
  "com.dimafeng" %% "testcontainers-scala-postgresql" % testcontainersScalaVersion % Test,
)