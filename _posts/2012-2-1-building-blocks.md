---
layout: default
title: ""
published: true
data:
  x: 3100
  y: -1000
  rotate: -35
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
  def pullreqs(project: GhProject): Future[Seq[PullRequest]]
  def branches(project: GhProject): Future[Seq[Branches]]
  def comments(pr: PullRequest): Future[PullRequestComment]]
  def comment(pc: PullRequestComment): Future[Unit]
}
{% endhighlight %}
