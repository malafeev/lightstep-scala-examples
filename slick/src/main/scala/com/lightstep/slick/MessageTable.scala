package com.lightstep.slick

import slick.jdbc.H2Profile.api._

final class MessageTable(tag: Tag) extends Table[Message](tag, "message") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

  def content = column[String]("content")

  def * = (content, id).mapTo[Message]
}
