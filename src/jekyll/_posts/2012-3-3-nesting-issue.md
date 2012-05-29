---
layout: default
title: ""
published: true
data:
  x: -50
  y: -2150
  z: 1000
  rotate: 10
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
