package generic

import concurrent.Future


object Contexts {
  type SingleThreaded[X] = X
  type Concurrent[X] = Future[X]
}
