package futures

import concurrent._

case class Statistics(
    user: String,
    watchers: Seq[Watcher], 
    pullreqs: Seq[PullRequest])

class SpecificStatisticsService(api: GhApi)(implicit ctx: ExecutionContext) {
  
  
    def statistics(user: String): Future[Statistics] = {
      val projects = api.projects(user)
      def fget[B](f: Project => Future[Seq[B]]): Future[Seq[B]] = for {
        ps <- projects
        items <- Future.traverse(ps)(f)
      } yield items.flatten
      for {
        (pulls, watchers) <- fget(api.pullrequests) zip fget(api.watchers) 
      } yield Statistics(user, watchers, pulls)
    }
}
