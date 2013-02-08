package futures

import org.specs2.mutable.Specification
import concurrent._
import concurrent.duration._
import Duration.Inf
import ExecutionContext.Implicits.global
import model._

object ServiceSpec extends Specification {
  
  "A Specific Statistics Service" should {
    "Find all projects" in {
      // Stubbed API
      val api = new GhApi {
        def projects(user: String) =
          Future(Seq(Project("jsuereth", "scala-arm"), Project("jsuereth", "intro-to-fp")))
        override def pullrequests(project: Project) = Future(project match {
          case p @ Project("jsuereth", "scala-arm") => 
            Seq(PullRequest(p, "1", "ThatOtherGuy", false))
          case _ => Seq.empty
        })
        override def collaborators(project: Project) = Future(project match {
          case p @ Project("jsuereth", "scala-arm") => 
            Seq(Collaborator("ThatOtherGuy"))
          case _ => Seq.empty
        })
      }
      val service = new SpecificStatisticsService(api)      
      val results = Await.result(service.userStatistics("Josh"), Inf)
      results.user must equalTo("Josh")
      results.projects.size must equalTo(2)
      
      results.projects must contain(ProjectStatistics(
          Project("jsuereth", "scala-arm"), 
         Seq(Collaborator("ThatOtherGuy")),
         Seq(PullRequest(Project("jsuereth", "scala-arm"), "1", "ThatOtherGuy", false)))
        )
    }
  }
}
