ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.8"

val testcontainersScalaVersion = "0.41.0"

lazy val root = (project in file("."))
  .settings(
    name := "scala-testcontainers-example-project"
  )

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.17" % Test,
  "com.dimafeng" %% "testcontainers-scala-scalatest" % testcontainersScalaVersion % Test,
  "com.dimafeng" %% "testcontainers-scala-postgresql" % testcontainersScalaVersion % Test,
)