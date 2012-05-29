---
layout: default
title: ""
published: true
data:
  x: 1550
  y: 650
  z: -1000
  rotate: -40
---

# Iteration #

The *traverse* operation.

{% highlight scala %}
trait Traverse[Coll[_]] {
  def traverse[Context[_]: Applicative,A,B](a: Coll[A])(f: A => Context[B]): Context[Coll[B]]
}
{% endhighlight %}