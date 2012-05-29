package futures

import akka.dispatch.{ExecutionContext,Future}
import akka.actor.ActorSystem

object StatApp extends App {
  implicit val system = ActorSystem()
  implicit val ctx = ExecutionContext.defaultExecutionContext
  
  val api = GhApi.make
  val service = new SpecificStatisticsService(api)
  service.statistics("jsuereth") foreach { stat =>
    println("Statistiscs = " + stat)
  }
}