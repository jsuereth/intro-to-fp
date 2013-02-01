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

scalaVersion := "2.10.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-actor_2.10" % "2.1.0",
  "net.databinder.dispatch" % "dispatch-core_2.10" % "0.9.5",
  "org.specs2" % "specs2_2.10" % "1.13" % "test"
)

scalacOptions += "-optimise"

ghpages.settings

git.remoteRepo := "git@github.com:jsuereth/intro-to-fp.git"
