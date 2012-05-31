---
layout: default
title: ""
published: true
data:
  x: 650
  y: 1750
  rotate: -30
  z: 100
---

# Generic Service #

{% highlight scala %}
class GenericStatisticsService[Context[_]: Monad](api: GhApi[Context]) {  
  def statistics(user: String): Context[Statistics] = {
      val projects = api.projects(user)
      def get[B](f: Project => Context[Seq[B]]): Context[Seq[B]] = for {
        ps <- projects
        items <- ps traverse f
      } yield items.flatten
      val pullRequests = get(api.pullrequests)
      val watchers = get(api.watchers)
      (pullRequests zip watchers) map { case (prs, ws) => Statistics(user, ws, prs)}
  }
}
{% endhighlight %}