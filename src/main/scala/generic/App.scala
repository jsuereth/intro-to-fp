package generic

import concurrent._
import concurrent.duration._
import Instances._

object StatApp extends App {
  implicit val ctx = ExecutionContext.Implicits.global
  val service = new GenericStatisticsService(GhApi.makeNonBlocking)
  val stats = Await.result(service.statistics("jsuereth"), 60 seconds) 
  println("Statistiscs = " + stats)
}
