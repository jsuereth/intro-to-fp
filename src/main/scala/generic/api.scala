package generic

import model._


trait GhApi[Context[_]] {
  def projects(user: String): Context[Seq[Project]]
  def pullrequests(proj: Project): Context[Seq[PullRequest]]
  
  def collaborators(proj: Project): Context[Seq[Collaborator]]
}

object GhApi {
  def makeNonBlocking: GhApi[concurrent.Future] = LiveGhApi 
}

