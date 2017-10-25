package com.lightstep.akka

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.lightstep.tracer.jre.JRETracer
import com.lightstep.tracer.shared.Options.OptionsBuilder
import io.opentracing.Tracer

case class Msg(msg: String)

class MainActor(tracer: Tracer) extends Actor {

  def receive = {
    case Msg(msg) =>
      val span = tracer.buildSpan("receive").startActive()
      span.setTag("actor_path", self.path.toSerializationFormat)
      println(msg)
      span.close()
  }
}

object AkkaApp extends App {

  val tracer = new JRETracer(new OptionsBuilder()
      .withComponentName("scala-akka")
      .withAccessToken("replace me !!!")
      .build())

  val system: ActorSystem = ActorSystem("akkaApp")

  val greater: ActorRef = system.actorOf(Props(new MainActor(tracer)), "MainActor")

  val span = tracer.buildSpan("send").startActive()
  span.setTag("actor_path", greater.path.toSerializationFormat)
  greater ! Msg("Akka")
  span.close()

  system.terminate()
}
