package generic

object Syntax {
  
  // Helpers put on "container" types.
  final implicit class ops[M[_], A](val ma: M[A]) extends AnyVal{
    def map[B](f: A => B)(implicit ev: Functor[M]): M[B] =
      ev.map(ma)(f)
    def flatMap[B](f: A => M[B])(implicit ev: Monad[M]): M[B] =
      ev.flatMap(ma)(f)
    def zip[B](other: M[B])(implicit ev: Applicative[M]) =
      (ma, other) ap { (a, b) => a -> b }
    def traverse[Context[_], B](f: A => Context[B])(implicit ev: Traverse[M], ev2: Applicative[Context]): Context[M[B]] =
      ev.traverse(ma)(f)
  }
  
  // Helpers put on Tuple2 types.
  final implicit class tuple2Ops[M[_], A, B](val tuple2: (M[A], M[B])) extends AnyVal {
    def ap[C](f: (A,B) => C)(implicit ap: Applicative[M]) =
      ap.ap(tuple2._2)(tuple2._1 map f.curried)
  }
  
  
  // Helpers put on Tuple3 types.
  final implicit class tuple3Ops[M[_], A, B, C](val tuple: (M[A], M[B], M[C])) extends AnyVal {
    def ap[D](f: (A,B,C) => D)(implicit ap: Applicative[M]) =
      ap.ap(tuple._3)(ap.ap(tuple._2)(tuple._1 map f.curried))
  }
  
  // Helpers put on any type.
  final implicit class any2Ops[A](val a: A) extends AnyVal {
    def point[P[_]: Pointed] = implicitly[Pointed[P]] point a
  }
}
