package generic

import Contexts.Concurrent
import Contexts.SingleThreaded
import concurrent._


object Instances {

  implicit object SeqAbstractions extends Monad[Seq] with Traverse[Seq] {
    def point[A](x: A): Seq[A] = Seq(x)
    override def map[A, B](x: Seq[A])(f: A => B): Seq[B] =
      x map f
    def flatMap[A, B](x: Seq[A])(f: A => Seq[B]): Seq[B] =
      x flatMap f
    def traverse[Context[_]: Applicative,A,B](a: Seq[A])(f: A => Context[B]): Context[Seq[B]] = {
      // TODO - best performance?
      val ap = implicitly[Applicative[Context]]
      val mapped: Seq[Context[B]] = a map f
      val init = ap point Seq.empty[B]
      mapped.foldLeft(init) { (current, el) =>
        val appendTransformed: Context[Seq[B] => Seq[B]] =
          ap.map(el) { t => (start: Seq[B]) => start :+ t }
        ap.ap(current)(appendTransformed)
      }
    }
  }
  
  
  import Contexts._
  implicit object SingleThreadedAbstractions 
    extends Monad[SingleThreaded] {
    def point[A](x: A): SingleThreaded[A] = x
    override def map[A, B](x: SingleThreaded[A])(f: A => B): SingleThreaded[B] =
      f(x)
    def flatMap[A, B](x: SingleThreaded[A])(f: A => SingleThreaded[B]): SingleThreaded[B] =
      f(x)
  }
  
  final class ConcurrentAbstractions(implicit executor: ExecutionContext) 
    extends Monad[Future] {
    def point[A](x: A): Future[A] = Future(x)
    override def map[A, B](x: Future[A])(f: A => B): Future[B] =
      x map f
    def flatMap[A, B](x: Future[A])(f: A => Future[B]): Future[B] =
      x flatMap f
  }
  implicit def concurrentAbs(implicit executor: ExecutionContext): ConcurrentAbstractions = 
    new ConcurrentAbstractions
}
