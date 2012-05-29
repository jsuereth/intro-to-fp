package futures

import akka.dispatch.{Future,Futures,ExecutionContext}


case class Statistics(
    user: String,
    watchers: Seq[Watcher], 
    pullreqs: Seq[PullRequest])

class SpecificStatisticsService(api: GhApi)(implicit ctx: ExecutionContext) {
  
    def statistics(user: String): Future[Statistics] = {
      val projects = api.projects(user)
      
      val pullRequests = for {
        ps <- projects
        prs <- Future.traverse(ps)(api.pullrequests)
      } yield prs.flatten
      
      val watchers = for {
        ps <- projects
        watchers <- Future.traverse(ps)(api.watchers)
      } yield watchers.flatten
      
      pullRequests zip watchers map { case (prs, ws) =>
        Statistics(user, ws, prs)
      }
    }
}