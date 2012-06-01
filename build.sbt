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

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-actor" % "2.0.1",
  "org.specs2" %% "specs2" % "1.10" % "test"
)

ghpages.settings

git.remoteRepo := "git@github.com:jsuereth/intro-to-fp.git"
