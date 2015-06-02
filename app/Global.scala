import java.util.concurrent.Executors
import play.api._
import scalikejdbc.ConnectionPool

object Global extends GlobalSettings {
  override def onStart(app: Application) = {
    // skinny に必要な設定
    // skinny.DBSettings.initialize()
    // implicit val session = AutoSession

    Class.forName("org.h2.Driver")
    val url = app.configuration.getString("db.default.url").getOrElse(throw new IllegalArgumentException)
    val user = app.configuration.getString("db.default.username").getOrElse(throw new IllegalArgumentException)
    val password = app.configuration.getString("db.default.password").getOrElse(throw new IllegalArgumentException)
    ConnectionPool.singleton(url, user, password)
  }
}
