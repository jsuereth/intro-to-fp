---
layout: default
title: ""
published: true
data:
  x: 1050
  y: 1250
  rotate: -60
  z: -1400
---

# Applicative Functors #

The *zip* operation

{% highlight scala %}
trait Applicative[Context[_]] {
  def point[A](raw_value: A): Context[A]
  
  def applyNested[A,B](
    value: Context[A],
    contexted_function: Context[A => B]
  ): Context[B]
}
{% endhighlight %}