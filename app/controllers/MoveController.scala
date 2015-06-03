package controllers

import play.api._
import play.api.mvc._
import models._
import play.api.libs.json._

class MoveController extends Controller {
  
  implicit val writer = new Writes[Move] {
    def writes(m: Move): JsValue = {
      Json.obj(
        "name" -> m.name,
        "command" -> m.command,
        "detection" -> m.detection
      )
    }
  }

  def get = Action {
    val moves: List[Move] = Move.findAll()
    val jsValue: JsValue = Json.toJson(moves)
    Ok(Json.stringify(jsValue)).
      withHeaders(CONTENT_TYPE -> "application/json; charset=utf-8")
  }

  def combo = Action {
    val recipe: Option[Recipe] = Recipe.joins(Recipe.movesRef).findById(2)
    println(recipe)
    Ok("hello")
  }
}
