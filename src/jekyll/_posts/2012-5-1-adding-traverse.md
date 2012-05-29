---
layout: default
title: ""
published: true
data:
  x: 1050
  y: -950
  z: 0
  rotate: 40
---

# Traversing #

{% highlight scala %}
object Future {
  def traverse[A,B](values: Seq[A])(asynchOp: A => Future[B]): Future[Seq[B]]
}
{% endhighlight %}