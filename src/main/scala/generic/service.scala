package generic

import Instances.SeqAbstractions
import Syntax.ops
import Syntax.tuple2Ops


case class Statistics(
    user: String,
    watchers: Seq[Watcher], 
    pullreqs: Seq[PullRequest])

    
class GenericStatisticsService[Context[_]: Monad](api: GhApi[Context]) {  
    
  def statistics(user: String): Context[Statistics] = {
      val projects = api.projects(user)
      
      val pullRequests = for {
        ps <- projects
        prs <- ps traverse api.pullrequests
      } yield prs.flatten
      
      val watchers = for {
        ps <- projects
        watchers <- ps traverse api.watchers
      } yield watchers.flatten
      
      (pullRequests, watchers) ap { (prs, ws) =>
        Statistics(user, ws, prs)
      }
  }
}
