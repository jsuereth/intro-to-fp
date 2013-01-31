package futures

import concurrent._
import concurrent.duration._

object StatApp extends App {
  implicit val ctx = ExecutionContext.Implicits.global
  val api = GhApi.make
  val service = new SpecificStatisticsService(api)
  val stats = Await.result(service.statistics("jsuereth"), 60 seconds) 
  println("Statistiscs = " + stats)
}
