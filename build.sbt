name := "intro-to-fp"

organization := "com.jsuereth"

site.settings

site.jekyllSupport()

com.jsuereth.sbtsite.SiteKeys.siteMappings <<= com.jsuereth.sbtsite.SiteKeys.siteMappings map { m =>
  for {
    (file, target) <- m
    if (target startsWith "css/") || !(target contains "/")
  } yield file -> target
}

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

scalaVersion := "2.10.2-RC2"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.2.0-RC1",
  "net.databinder.dispatch" %% "dispatch-core" % "0.10.1",
  "org.specs2" %% "specs2" % "2.0-RC1" % "test"
)

scalacOptions += "-optimise"

ghpages.settings

git.remoteRepo := "git@github.com:jsuereth/intro-to-fp.git"
