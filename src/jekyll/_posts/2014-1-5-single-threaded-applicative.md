---
layout: default
title: ""
published: true
data:
  x: 650
  y: 1750
  rotate: -30
  z: 1200
---

# SingleThreaded Monad #

*flatMap* method

{% highlight scala %}
def flatMap[A,B](a: SingleThreaded[A],
                f: A => SingleThreaded[B]): SingleThreaded[B] =
{% endhighlight %}

* `SingleThreaded[A]` *is*  `A`
* `A => SingleThreaded[B]` *is* `A => B`
* `flatMap` *is* `f(a)`


