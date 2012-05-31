---
layout: default
title: ""
published: true
data:
  x: 1050
  y: 1250
  rotate: -60
  z: -1000
---

# Applicative Functors #

The *zip* operation

{% highlight scala %}
trait Applicative[F[_]] {
  def point[A](a: A): F[A]
  def ap[A,B](fa: F[A])(f: F[A => B]): F[B]
}
{% endhighlight %}