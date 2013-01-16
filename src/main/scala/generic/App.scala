package generic

import concurrent._
import Instances._

object StatApp extends App {
  implicit val ctx = ExecutionContext.Implicits.global
  
  val api = GhApi.makeNonBlocking
  val service = new GenericStatisticsService(api)
  service.statistics("jsuereth") foreach { stat =>
    println("Statistiscs = " + stat)
  }
}
