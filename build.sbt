name := """TekkenCombo"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "org.twitter4j"    % "twitter4j-core"               % "4.0.3",
  "org.scalikejdbc" %% "scalikejdbc"                  % "2.2.6",
  "org.scalikejdbc" %% "scalikejdbc-config"           % "2.2.6",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.4.+",
  "mysql" % "mysql-connector-java" % "5.1.20",
  "org.skinny-framework" %% "skinny-orm"      % "1.3.18",
  evolutions
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
