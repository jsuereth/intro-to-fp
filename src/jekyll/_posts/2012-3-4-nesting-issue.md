---
layout: default
title: ""
published: true
data:
  x: 1150
  y: -950
  z: 2000
  rotate: 40
---

# Nesting! #

{% highlight scala %}
 val projects: Future[Seq[Project]] =
   api.projects(user)

 val firstpullreqs: Future[Future[Seq[PullRequests]]] =
   projects map { projects =>
     api.getpullreqs(projects.head)
   }

val flattenedPullReqs: Future[Seq[PullRequests]] =
  ???

{% endhighlight %}
