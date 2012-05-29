package generic

object Syntax {
  
  // Helpers put on "container" types.
  final class ContainedOps[M[_], A](ma: M[A]) {
    def map[B](f: A => B)(implicit ev: Functor[M]): M[B] =
      ev.map(ma)(f)
    def flatMap[B](f: A => M[B])(implicit ev: Monad[M]): M[B] =
      ev.flatMap(ma)(f)
    def zip[B](other: M[B])(implicit ev: Applicative[M]) =
      (ma, other) ap { (a, b) => a -> b }
    def traverse[Context[_], B](f: A => Context[B])(implicit ev: Traverse[M], ev2: Applicative[Context]): Context[M[B]] =
      ev.traverse(ma)(f)
  }
  implicit def ops[M[_], A](m: M[A]): ContainedOps[M,A] =
    new ContainedOps(m)
  
  // Helpers put on Tuple2 types.
  final class Tuple2Ops[M[_], A, B](tuple2: (M[A], M[B])) {
    def ap[C](f: (A,B) => C)(implicit ap: Applicative[M]) =
      ap.ap(tuple2._2)(tuple2._1 map f.curried)
  }
  implicit def tuple2Ops[M[_], A, B](t2: (M[A], M[B])): Tuple2Ops[M,A,B] =
    new Tuple2Ops(t2)
  
  
  // Helpers put on Tuple3 types.
  final class Tuple3Ops[M[_], A, B, C](tuple: (M[A], M[B], M[C])) {
    def ap[D](f: (A,B,C) => D)(implicit ap: Applicative[M]) =
      ap.ap(tuple._3)(ap.ap(tuple._2)(tuple._1 map f.curried))
  }
  implicit def tuple3Ops[M[_], A, B, C](t: (M[A], M[B], M[C])): Tuple3Ops[M,A,B,C] =
    new Tuple3Ops(t)
  
  // Helpers put on any type.
  final class Any2Ops[A](a: A) {
    def point[P[_]: Pointed] = implicitly[Pointed[P]] point a
  }
  implicit def any2Ops[A](a: A): Any2Ops[A] = 
    new Any2Ops(a)
}