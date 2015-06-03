package models

import skinny.orm.SkinnyCRUDMapper
import scalikejdbc._
import skinny.orm._, feature._
import org.joda.time._

case class Recipe(
  id: Long,
  moves: Seq[Move] = Nil,
  author: Long // TODO: replace to FK for user_id
)

case class RecipeMove(recipeId: Long, moveId: Long)
object RecipeMove extends SkinnyJoinTable[RecipeMove] {
  override lazy val tableName = "recipes_moves"
  override lazy val defaultAlias = createAlias("rm")
}

object Recipe extends SkinnyCRUDMapper[Recipe] {
  override def defaultAlias = createAlias("r")
  override def tableName = "recipes"

  lazy val movesRef = hasManyThrough[Move](
    through = RecipeMove,
    many = Move,
    merge = (recipe, moves) => recipe.copy(moves = moves)
  )

  override def extract(rs:WrappedResultSet, n:ResultName[Recipe]): Recipe =
    new Recipe(
      id = rs.long(n.id),
      author = rs.long(n.author)
    )
}

