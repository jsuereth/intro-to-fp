package generic

import akka.dispatch.{ExecutionContext,Future}
import akka.actor.ActorSystem
import Instances._

object StatApp extends App {
  implicit val system = ActorSystem()
  implicit val ctx = ExecutionContext.defaultExecutionContext
  
  val api = GhApi.makeNonBlocking
  val service = new GenericStatisticsService(api)
  service.statistics("jsuereth") foreach { stat =>
    println("Statistiscs = " + stat)
  }
}