name := "Play LightStep Example"
organization := "com.lightstep"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.4"

libraryDependencies += guice

libraryDependencies ++= Seq(
  "com.lightstep.tracer" % "lightstep-tracer-jre" % "0.12.15",
  "io.grpc" % "grpc-netty" % "1.4.0",
  "io.netty" % "netty-tcnative-boringssl-static" % "2.0.5.Final"
)
