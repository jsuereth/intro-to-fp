package futures

import concurrent._

object StatApp extends App {
  implicit val ctx = ExecutionContext.Implicits.global
  
  val api = GhApi.make
  val service = new SpecificStatisticsService(api)
  service.statistics("jsuereth") foreach { stat =>
    println("Statistiscs = " + stat)
  }
}
