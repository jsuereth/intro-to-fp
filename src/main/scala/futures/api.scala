package futures

import concurrent.Future


case class Project(owner: String, name: String)
case class PullRequest(project: Project, id: String)
case class Collaborator(name: String)

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
    generic.LiveGhApi.projects(user) map { _ map (p => Project(p.owner, p.name)) }
  def pullrequests(proj: Project): Future[Seq[PullRequest]] = 
    generic.LiveGhApi.pullrequests(generic.Project(proj.owner, proj.name)) map {
      _ map (pr => PullRequest(Project(pr.project.owner, pr.project.name), pr.id))
    }
  def collaborators(proj: Project): Future[Seq[Collaborator]] = 
    generic.LiveGhApi.collaborators(generic.Project(proj.owner, proj.name)) map {
      _ map (c => Collaborator(c.name))
    }
}