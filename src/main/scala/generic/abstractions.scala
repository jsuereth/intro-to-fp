package generic

/** Represents a higher-kinded type that can take any value and encapsulate it. */
trait Pointed[P[_]] {
  def point[A](a: A): P[A]
}


/** represents a container of a value where the contained
 * value(s) may be altered.
 */
trait Functor[F[_]] {
  def map[A,B](a: F[A])(f: A => B): F[B]
}


/** Represents the ability to *join* a computation in some
 * context.
 */
trait Applicative[A[_]] extends Pointed[A] with Functor[A] {
  def ap[B,C](fa: A[B])(f: A[B => C]): A[C]
}

/** Represents a 'container' that can thread computations 
 * 
 */
trait Monad[M[_]] extends Applicative[M] {
  /** 'lifts' a value into a monadic context. */
  def flatMap[A,B](ma: M[A])(f: A => M[B]): M[B]
  
  
  // Default horrible implementation.
  override def map[A,B](ma: M[A])(f: A => B): M[B] =
    flatMap(ma) { a => point(f(a)) }
  override def ap[B,C](ma: M[B])(f: M[B => C]): M[C] =
    flatMap(ma) { a => map(f) { f => f(a) } }
}

/** Represents something that can be traversed inside some context. */
trait Traverse[Coll[_]] {
  def traverse[Context[_]: Applicative,A,B](a: Coll[A])(f: A => Context[B]): Context[Coll[B]]
}


