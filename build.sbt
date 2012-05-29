name := "intro-to-fp"

organization := "com.jsuereth"

site.settings

site.jekyllSupport()

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.typesafe.akka" % "akka-actor" % "2.0.1"

ghpages.settings

git.remoteRepo := "git@github.com:jsuereth/intro-to-fp.git"
