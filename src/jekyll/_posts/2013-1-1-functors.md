---
layout: default
title: ""
published: true
data:
  x: 50
  y: 2250
  rotate: -20
  z: -1400
---

# Functors #

The *map* operation

{% highlight scala %}
trait Functor[Context[_]] {
  def map[A,B](
     value: Context[A],
     raw_function: A => B
  ): Context[B]
}
{% endhighlight %}