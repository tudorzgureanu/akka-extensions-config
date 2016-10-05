name := """Akka Extensions Config"""

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.10",
  "net.cakesolutions" %% "scala-kafka-client" % "0.10.0.0",
  "net.cakesolutions" %% "scala-kafka-client-akka" % "0.10.0.0",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.10" % "test"
)

resolvers += Resolver.bintrayRepo("cakesolutions", "maven")