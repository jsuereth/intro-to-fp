package futures

import concurrent.Future
import model._

trait GhApi {
  def projects(user: String): Future[Seq[Project]]
  def pullrequests(proj: Project): Future[Seq[PullRequest]]
  def collaborators(proj: Project): Future[Seq[Collaborator]]
}

object GhApi {
  def make: GhApi = LivGhApi
}

// We delegate for laziness.  The other API has more typing than this one, so it has the impl.
object LivGhApi extends GhApi {
  implicit val ctx = concurrent.ExecutionContext.Implicits.global
  def projects(user: String): Future[Seq[Project]] = 
    generic.LiveGhApi.projects(user)
  def pullrequests(proj: Project): Future[Seq[PullRequest]] = 
    generic.LiveGhApi.pullrequests(proj)
  def collaborators(proj: Project): Future[Seq[Collaborator]] = 
    generic.LiveGhApi.collaborators(proj)
}