package controllers

import java.util.UUID
import models._
import play.Play
import play.api._
import play.api.mvc._
import play.api.cache.Cache
import twitter4j.auth._
import twitter4j._
import scala.concurrent.duration._
import play.api.Play.current
import scalikejdbc._
import scalikejdbc.interpolation.SQLSyntax

class EntryPointController extends Controller {
  val CookieKeySessionId = "tekkenrecipe.sessionId"
  val CacheKeyPrefixTwitter = "twitterInstance"
  val documentRootUrl =
    Play.application().configuration().getString("tekkenrecipe.documentrooturl")
  val consumerKey =
    Play.application().configuration().getString("tekkenrecipe.consumerkey")
  val consumerSecret =
    Play.application().configuration().getString("tekkenrecipe.consumersecret")

//  implicit val currentApplication: Application = play.api.Play.current


  def index = Action { request =>
    val sessionId: Option[String] =
      request.cookies.get(CookieKeySessionId).map(c => c.value)
    val accessToken: Option[AccessToken] =
      sessionId.flatMap(id => Cache.get(id).asInstanceOf[Option[AccessToken]])

    if (!accessToken.isEmpty) {
      // user を登録
      (accessToken.get.getScreenName, accessToken.get.getUserId) match {
        case (name, twitter_id) => 
          val user: Option[models.User] =
            models.User.where('twitter_id -> twitter_id).apply().headOption
          println(user)
          user match {
            case Some(user) => ???
            case None => models.User.createWithAttributes(
              'twitter_id -> twitter_id,
              'name -> name
            )
          }
        case (_, _) => println("could not get username")
      }

    }

    Ok(views.html.index(accessToken))
      .withCookies(Cookie(CookieKeySessionId,
        sessionId.getOrElse(UUID.randomUUID().toString), Some(30 * 60)))
  }

  def login = Action { request =>
    val sessionIdOpt: Option[String] =
      request.cookies.get(CookieKeySessionId).map(c => c.value)
    sessionIdOpt match {
      case Some(sessionId) => {
        val twitter = createTwitter
        val requestToken =
          twitter.getOAuthRequestToken(documentRootUrl + routes.EntryPointController.oauthCallback(None).url)
        Cache.set(cacheKeyTwitter(sessionId), twitter, 30 seconds)
        Redirect(requestToken.getAuthenticationURL)
          .withCookies(Cookie(CookieKeySessionId, sessionId, Some(30 * 60)))
      }
      case None => Forbidden("Require Session id.")
    }
  }
  
  /**
   * ログアウト
   * @return
   */
  def logout = Action { request =>
    val sessionId: Option[String] = request.cookies.get(CookieKeySessionId).map(c => c.value)
    sessionId match {
      case Some(id) => Cache.remove(id)
      case None => Unit
    }
    Ok(views.html.index(None))
  }

  /**
    * twitter からのコールバック
    * @param oauthVerifier
    * @return
    */
  def oauthCallback(oauthVerifier: Option[String]) = Action { request =>
    val sessionIdOpt: Option[String] = request.cookies.get(CookieKeySessionId).map(c => c.value)
    val twitterOpt: Option[Twitter] = sessionIdOpt.flatMap(id => Cache.get(cacheKeyTwitter(id))).asInstanceOf[Option[Twitter]]
      (oauthVerifier, sessionIdOpt, twitterOpt) match {
      case (Some(verifier), Some(sessionId), Some(twitter)) =>
        val accessToken = twitter.getOAuthAccessToken(verifier)
        Cache.set(sessionId, accessToken, 30 minutes)
        Redirect(routes.EntryPointController.index().url)
          .withCookies(Cookie(CookieKeySessionId, sessionId, Some(30 * 60)))
      case (_, _, _) => BadRequest("認証のために必要な情報を取得できませんでした")
    }
  }

  private[this] def createTwitter: Twitter = {
    val twitter = new TwitterFactory().getInstance()
    twitter.setOAuthConsumer(
      consumerKey,
      consumerSecret
    )
    twitter
  }

  private[this] def cacheKeyTwitter(sessionId: String): String = CacheKeyPrefixTwitter + sessionId
}
