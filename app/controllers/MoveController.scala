package controllers

import play.api._
import play.api.mvc._
import models._

class MoveController extends Controller {
  def get = Action {
//    Move.findById(1)
    Ok("Hello")
  }
}
