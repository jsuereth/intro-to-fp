---
layout: default
title: ""
published: true
data:
  x: -750
  y: 2450
  rotate: 0
  z: 100
---

# What is a SingleThreaded context? #

{% highlight scala %}
object Contexts {
  type SingleThreaded[A] = A
  type Future[A] = akka.dispatch.Future[A]
}
{% endhighlight %}