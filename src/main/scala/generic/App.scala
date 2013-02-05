package generic

import concurrent._
import concurrent.duration._
import Instances._

object StatApp extends App {
  implicit val ctx = ExecutionContext.Implicits.global
  val service = new GenericStatisticsService(GhApi.makeNonBlocking)
  for(arg <- args) {
    val stats = Await.result(service.userStatistics(arg), 60 seconds) 
    println()
    println(stats)
    println()
  }
}
