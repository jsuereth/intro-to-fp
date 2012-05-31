---
layout: default
title: ""
published: true
data:
  x: 1550
  y: 650
  rotate: -40
  z: 1500
---

# SingleThreaded Applicative Functor #

*flatMap* method

{% highlight scala %}
def ap[A,B](
  a: SingleThreaded[A],
  f: SingleThreaded[A => B]): SingleThreaded[B] =
{% endhighlight %}

* `SingleThreaded[A]` *is*  `A`
* `SingleThreaded[A => B]` *is* `A => B`
* `ap` *is* `f(a)`


