package generic

import akka.dispatch.Future


object Contexts {
  type SingleThreaded[X] = X
  type Concurrent[X] = Future[X]
}