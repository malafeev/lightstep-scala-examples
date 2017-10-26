package com.lightbend.akka.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.lightstep.tracer.jre.JRETracer
import com.lightstep.tracer.shared.Options.OptionsBuilder
import io.opentracing.tag.Tags

import scala.io.StdIn

object AkkaHttp extends App {
  implicit val system = ActorSystem("akkaHttp")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  val tracer = new JRETracer(new OptionsBuilder()
      .withComponentName("scala-akka-http")
      .withAccessToken("replace me!!!")
      .build())

  val route =
    path("") {
      get {
        extractRequest { request =>

          val span = tracer.buildSpan(request.method.value)
              .withTag(Tags.HTTP_METHOD.getKey, request.method.value)
              .withTag(Tags.HTTP_URL.getKey, request.uri.path.toString())
              .startActive()

          try {
            complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "Akka-http"))
          } finally {
            span.close()
          }
        }
      }
    }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
}

