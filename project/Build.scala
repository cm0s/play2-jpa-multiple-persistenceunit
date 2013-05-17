import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play2-jpa-multiple-persistenceunit"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    javaCore,
    javaJdbc,
    javaJpa,
    "org.hibernate" % "hibernate-entitymanager" % "4.1.7.Final",
    "mysql" % "mysql-connector-java" % "5.1.9"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    ebeanEnabled := false
  )

}
