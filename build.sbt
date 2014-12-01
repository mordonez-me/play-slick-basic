name := """play-slick-basic"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  	"mysql" % "mysql-connector-java" % "5.1.18",
  	"com.typesafe.slick" %% "slick" % "2.1.0"
)
