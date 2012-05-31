---
layout: default
title: ""
published: true
data:
  x: 2400
  y: 0
  rotate: -25
  z: 1200
---

# Now a single-threaded test #

{% highlight scala %}
val api: GhApi[SingleThreaded] = new StubbedApi
val service = new GenericStatisticsService(api)
val expected = Statistics("Josh",Seq(Watcher()), Seq(PullRequest()))
val results = service.statistics("Josh")
results must equalTo(expected)
{% endhighlight %}