---
layout: default
title: ""
published: true
data:
  x: 50
  y: 2250
  rotate: -20
  z: 1500
---

# SingleThreaded Functor #

*map* method

{% highlight scala %}
def map[A,B](a: SingleThreaded[A])(f: A => B): SingleThreaded[B] =
{% endhighlight %}

Since `SingleThreaded[A]` *is*  `A`,  `map` *is* `f(a)`


