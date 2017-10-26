name := """play"""
organization := "com.lightstep"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.3"

libraryDependencies += guice

libraryDependencies ++= Seq(
  "com.lightstep.tracer" % "lightstep-tracer-jre" % "0.12.15",
  "io.grpc" % "grpc-netty" % "1.4.0",
  "io.netty" % "netty-tcnative-boringssl-static" % "2.0.5.Final"
)