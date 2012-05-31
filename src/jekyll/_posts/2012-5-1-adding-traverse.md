---
layout: default
title: ""
published: true
data:
  x: -750
  y: -1650
  rotate: -50
  z: -200
---

# Traversing #

{% highlight scala %}
object Future {
  def traverse[A,B](values: Seq[A])(asynchOp: A => Future[B]): Future[Seq[B]]
}
{% endhighlight %}