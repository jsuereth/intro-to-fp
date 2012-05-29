---
layout: default
title: ""
published: true
data:
  x: 1050
  y: 1250
  z: -1000
  rotate: -60
---

# Applicative Functors #

The *zip* operation

{% highlight scala %}
trait Applicative[A[_]] {
  def point[A](a: A): A[A]
  def ap[B,C](fa: A[B])(f: A[B => C]): A[C]
}
{% endhighlight %}