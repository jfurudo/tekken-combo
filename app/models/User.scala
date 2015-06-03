package models

import scalikejdbc._
import skinny.orm._, feature._
import org.joda.time._

case class User(
  id: Long,
  twitter_id: Long,
  name: String
)

object User extends SkinnyCRUDMapper[User] {
  override lazy val tableName = "user"
  override lazy val defaultAlias = createAlias("u")

  override def extract(rs:WrappedResultSet, n:ResultName[User]): User =
    autoConstruct(rs, n)
}
