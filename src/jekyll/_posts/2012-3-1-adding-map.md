---
layout: default
title: ""
published: true
data:
  x: -50
  y: -2150
  z: -1000
  rotate: -10
---

# Adding some Asynch #

{% highlight scala %}
trait Future[A] {
  def map(f: A => B): Future[B]
}
{% endhighlight %}
