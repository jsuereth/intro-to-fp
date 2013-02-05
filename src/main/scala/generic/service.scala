package generic

import Instances.SeqAbstractions
import Syntax.ops
import Syntax.tuple2Ops
import model._

    
class GenericStatisticsService[Context[_]: Monad](api: GhApi[Context]) {  

  def projectStatistics(project: Project): Context[ProjectStatistics] = {
    val pullRequests = api pullrequests project
    val collaborators = api collaborators project
    pullRequests zip collaborators map {
      case (prs, cs) => ProjectStatistics(project, cs, prs)
    }
  }
  
  def userStatistics(user: String): Context[Statistics] =   
    for {
      projects <- api projects user
      stats <- projects traverse projectStatistics
    } yield Statistics(user, stats)
    
}
