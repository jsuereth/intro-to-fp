package futures

import concurrent._
import model._

class SpecificStatisticsService(api: GhApi)(implicit ctx: ExecutionContext) {

  def projectStatistics(project: Project): Future[ProjectStatistics] = {
    val pullRequests = api pullrequests project
    val collaborators = api collaborators project
    pullRequests zip collaborators map {
      case (prs, cs) => ProjectStatistics(project, cs, prs)
    }
  }
  
  def userStatistics(user: String): Future[Statistics] =   
    for {
      projects <- api projects user
      stats <- Future.traverse(projects)(projectStatistics)
    } yield Statistics(user, stats)
}
