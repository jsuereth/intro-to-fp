package futures

import concurrent._

case class Statistics(
    user: String,
    collaborators: Seq[Collaborator], 
    pullreqs: Seq[PullRequest]) {
  override def toString = s"$user - has ${collaborators.size} collaborators and ${pullreqs.size} open pull requests against his projects."
}

class SpecificStatisticsService(api: GhApi)(implicit ctx: ExecutionContext) {
  def statistics(user: String): Future[Statistics] = {
    val projects = api.projects(user)
    
    def fget[B](f: Project => Future[Seq[B]]): Future[Seq[B]] =
      for {
        ps <- projects
        items <- Future.traverse(ps)(f)
      } yield items.flatten
      
    for {
      (pulls, collaborators) <- fget(api.pullrequests) zip fget(api.collaborators) 
    } yield Statistics(user, collaborators, pulls)
  }
}
