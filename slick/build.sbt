name := "Slick LightStep Example"

version := "1.0"

scalaVersion := "2.12.4"

lazy val akkaVersion = "2.5.6"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.2.1",
  "com.h2database" % "h2" % "1.4.185",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "io.opentracing.contrib" % "opentracing-jdbc" % "0.0.5",
  "com.lightstep.tracer" % "lightstep-tracer-jre" % "0.12.15",
  "io.grpc" % "grpc-netty" % "1.4.0",
  "io.netty" % "netty-tcnative-boringssl-static" % "2.0.5.Final"
)

