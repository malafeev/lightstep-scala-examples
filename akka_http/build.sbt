name := "Akka Http LightStep Example"

version := "1.0"

scalaVersion := "2.12.4"

lazy val akkaVersion = "2.5.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.0.10", 
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.lightstep.tracer" % "lightstep-tracer-jre" % "0.12.15",
  "io.grpc" % "grpc-netty" % "1.4.0",
  "io.netty" % "netty-tcnative-boringssl-static" % "2.0.5.Final"
)

