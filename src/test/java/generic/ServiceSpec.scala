package generic

import org.specs2.mutable.Specification
import Instances.SingleThreadedAbstractions

object ServiceSpec extends Specification {
  
  "A Generic Statistics Service" should {
    "Find all projects" in {
      // Stubbed API
      val api = new GhApi[Contexts.SingleThreaded] {
        def projects(user: String) =
          Seq(Project("jsuereth", "scala-arm"), Project("jsuereth", "intro-to-fp"))
        override def pullrequests(project: Project) = project match {
          case p @ Project("jsuereth", "scala-arm") => Seq(PullRequest(p, "1"))
          case _ => Seq.empty
        }
        override def collaborators(project: Project) = project match {
          case p @ Project("jsuereth", "intro-to-fp") => Seq(Collaborator("ThatOtherGuy"))
          case _ => Seq.empty
        }
      }
      val service = new GenericStatisticsService(api)
      val results = service.statistics("Josh")
      results must equalTo(Statistics("Josh",Seq(Collaborator("ThatOtherGuy")), Seq(PullRequest(Project("jsuereth", "scala-arm"), "1"))))
    }
  }
}