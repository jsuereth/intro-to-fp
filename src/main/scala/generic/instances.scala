package generic

import Contexts.Concurrent
import Contexts.SingleThreaded
import akka.dispatch.ExecutionContext
import akka.dispatch.Future


object Instances {

  implicit object SeqAbstractions extends Monad[Seq] with Traverse[Seq] {
    def point[A](x: A): Seq[A] = Seq(x)
    override def map[A, B](x: Seq[A])(f: A => B): Seq[B] =
      x map f
    def flatMap[A, B](x: Seq[A])(f: A => Seq[B]): Seq[B] =
      x flatMap f
    def traverse[Context[_]: Applicative,A,B](a: Seq[A])(f: A => Context[B]): Context[Seq[B]] = {
      // TODO - improve performance...
      val ap = implicitly[Applicative[Context]]
      val init = ap point Seq.empty[B]
      a.foldLeft(init) { (current, el) =>
        val transformed: Context[B] = f(el)
        val appendTransformed: Context[Seq[B] => Seq[B]] =
          ap.map(transformed) { t => (start: Seq[B]) => start :+ t }
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
    extends Monad[Concurrent] {
    def point[A](x: A): Concurrent[A] = Future(x)
    override def map[A, B](x: Concurrent[A])(f: A => B): Concurrent[B] =
      x map f
    def flatMap[A, B](x: Concurrent[A])(f: A => Concurrent[B]): Concurrent[B] =
      x flatMap f
  }
  implicit def concurrentAbs(implicit executor: ExecutionContext): ConcurrentAbstractions = 
    new ConcurrentAbstractions
}