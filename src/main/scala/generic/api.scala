package generic

case class Project(name: String)
case class PullRequest()
case class Watcher()


trait GhApi[Context[_]] {
  def projects(user: String): Context[Seq[Project]]
  def pullrequests(proj: Project): Context[Seq[PullRequest]]
  
  def watchers(proj: Project): Context[Seq[Watcher]]
}

object GhApi {
  def makeNonBlocking: GhApi[akka.dispatch.Future] =
    null 
}

