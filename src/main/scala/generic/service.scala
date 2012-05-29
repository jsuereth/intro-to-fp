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
      def get[B](f: Project => Context[Seq[B]]): Context[Seq[B]] = for {
        ps <- projects
        items <- ps traverse f
      } yield items.flatten
      val pullRequests = get(api.pullrequests)
      val watchers = get(api.watchers)
      (pullRequests zip watchers) map { case (prs, ws) =>
        Statistics(user, ws, prs)
      }
  }
}
