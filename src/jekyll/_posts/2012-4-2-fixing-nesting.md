---
layout: default
title: ""
published: true
data:
  x: 1050
  y: -950
  rotate: 40
  z: 500
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