---
layout: default
title: ""
published: true
data:
  x: 450
  y: -1450
  z: 0
  rotate: 20
---

# A fix for nesting! #

{% highlight scala %}
val projects: Future[Seq[Project]] =
  api.projects(user)

val firstpullreqs: Future[Seq[PullRequests]] =
  projects flatMap { projects =>
    api.getpullreqs(projects.head)
  }

{% endhighlight %}