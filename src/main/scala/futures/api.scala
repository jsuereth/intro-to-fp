package futures

import concurrent.Future

case class Project(name: String)
case class PullRequest()
case class Watcher()

trait GhApi {
  def projects(user: String): Future[Seq[Project]]
  def pullrequests(proj: Project): Future[Seq[PullRequest]]
  def watchers(proj: Project): Future[Seq[Watcher]]
}

object GhApi {
  def make: GhApi = null
}
