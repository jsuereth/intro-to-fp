---
layout: default
title: ""
published: true
data:
  x: 1600
  y: -450
  z: -1010
  rotate: 60
---

# Zipping/Joining #

{% highlight scala %}
trait Future[A] {
  def zip[B](other: Future[B]): Future[(A,B)]
}
{% endhighlight %}