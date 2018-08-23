name := "toyrobot"

version := "0.1"

scalaVersion := "2.12.6"

organization := "com.github.benhutchison"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "4.2.0" % "test",
)

//required by Specs2
scalacOptions in Test ++= Seq("-Yrangepos")


