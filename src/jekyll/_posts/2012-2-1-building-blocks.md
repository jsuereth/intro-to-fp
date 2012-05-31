---
layout: default
title: ""
published: true
data:
  x: 5550
  y: 2000
  z: 2400
  rotate: 0
---

# A few building blocks #

{% highlight scala %}
trait Future[A] {
  def isDone: Boolean
  def await: A
}
{% endhighlight %}

{% highlight scala %}
trait GithubService {
  def projects(user: User): Future[Seq[Project]]
  def pullreqs(project: Project): Future[Seq[PullRequest]]
  def watchers(project: Project): Future[Seq[User]]
}
{% endhighlight %}
