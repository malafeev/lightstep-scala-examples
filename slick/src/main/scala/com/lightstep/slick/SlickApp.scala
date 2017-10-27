package com.lightstep.slick

import com.lightstep.tracer.jre.JRETracer
import com.lightstep.tracer.shared.Options.OptionsBuilder
import io.opentracing.util.GlobalTracer
import slick.jdbc.H2Profile.api._
import slick.lifted.TableQuery

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object SlickApp extends App {

  val tracer = new JRETracer(new OptionsBuilder()
      .withComponentName("scala-slick")
      .withAccessToken("replace me !!!")
      .build())

  GlobalTracer.register(tracer)

  val db = Database.forConfig("db")

  val messages = TableQuery[MessageTable]

  val action: DBIO[Unit] = messages.schema.create

  val future: Future[Unit] = db.run(action)

  val result: Unit = Await.result(future, 2.seconds)

  val insert: DBIO[Option[Int]] = messages ++= testData
  val insertFuture: Future[Option[Int]] = db.run(insert)
  val rowCount = Await.result(insertFuture, 2.seconds)

  val messagesAction: DBIO[Seq[Message]] = messages.result
  val messagesFuture: Future[Seq[Message]] = db.run(messagesAction)
  val messagesResults = Await.result(messagesFuture, 2.seconds)

  messagesResults.foreach(result => println(result))

  def testData = Seq(
    Message("Message 1"),
    Message("Message 2"),
    Message("Message 3"),
    Message("Message 4")
  )

}
