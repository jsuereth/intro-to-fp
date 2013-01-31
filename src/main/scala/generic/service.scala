package generic

import Instances.SeqAbstractions
import Syntax.ops
import Syntax.tuple2Ops


case class Statistics(
    user: String,
    collaborators: Seq[Collaborator], 
    pullreqs: Seq[PullRequest]) {
  override def toString = s"$user - has ${collaborators.size} collaborators and ${pullreqs.size} open pull requests against his projects."
}

    
class GenericStatisticsService[Context[_]: Monad](api: GhApi[Context]) {  
    
  def statistics(user: String): Context[Statistics] = {
    val projects = api.projects(user)
    
    def get[B](f: Project => Context[Seq[B]]): Context[Seq[B]] = 
      for {
        ps <- projects
        items <- ps traverse f
      } yield items.flatten
    
    val pullRequests = get(api.pullrequests)
    val collaborators = get(api.collaborators)
    (pullRequests zip collaborators) map { case (prs, cs) =>
      Statistics(user, cs, prs)
    }
  }
}
