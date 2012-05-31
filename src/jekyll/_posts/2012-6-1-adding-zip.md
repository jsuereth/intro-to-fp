---
layout: default
title: ""
published: true
data:
  x: 1250
  y: -950
  rotate: 40
  z: -2600
---

# Zipping/Joining #

{% highlight scala %}
trait Future[A] {
  def zip[B](other: Future[B]): Future[(A,B)]
}
{% endhighlight %}