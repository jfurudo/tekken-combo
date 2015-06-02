package models

import skinny.orm.SkinnyCRUDMapper
import scalikejdbc._
import skinny.orm._, feature._
import org.joda.time._

// case class MoveId(value: Int)

case class Move(
  id: Long,
  name: String,
  command: String,
  detection: String,
  damage: String,
  startup: String,
  hit: String,
  gurad: String,
  counter: String,
  note: String)


object Move extends SkinnyCRUDMapper[Move] {
  override def defaultAlias = createAlias("m")
  override def tableName = "moves"

  override def extract(rs:WrappedResultSet, n:ResultName[Move]):Move = new Move(
    id = rs.int(n.id),
    name = rs.string(n.name),
    command = rs.string(n.command),
    detection = rs.string(n.detection),
    damage = rs.string(n.damage),
    startup = rs.string(n.startup),
    hit = rs.string(n.hit),
    gurad = rs.string(n.gurad),
    counter = rs.string(n.counter),
    note = rs.string(n.note)
  )
}
