---
layout: default
title: ""
published: true
data:
  x: 450
  y: -1450
  z: -1000
  rotate: 20
---

# Chaining Asynchronous Calls #

{% highlight scala %}
object Future {
  def flatten[A](f: Future[Future[A]]): Future[A]
}
trait Future[A] {
  def flatMap(f: A => Future[B]): Future[B]
}
{% endhighlight %}