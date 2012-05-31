---
layout: default
title: ""
published: true
data:
  x: 1550
  y: 650
  rotate: -40
  z: -1400
---

# Iteration #

The *traverse* operation.

{% highlight scala %}
trait Traverse[Collection[_]] {
  def traverse[Context[_]: Applicative,A,B](
     collection: Collection[A],
     operation: A => Context[B]
  ): Context[Collection[B]]
}
{% endhighlight %}