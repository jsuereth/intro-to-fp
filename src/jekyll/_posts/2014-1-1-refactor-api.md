---
layout: default
title: ""
published: true
data:
  x: 1550
  y: 650
  rotate: -40
  z: 100
---

# A mite o' refactoring #

Generalize the github API trait

{% highlight scala %}
trait GhApi[Context[_]] {
  def projects(user: String): Context[Seq[Project]]
  def pullreqs(proj: Project): Context[Seq[PullRequest]]
  def watchers(proj: Project): Context[Seq[Watcher]]
}

class AsyncGhApi extends GhApi[Future] { ... }

class TestingGhApi extends GhApi[SingleThreaded] { ... }
{% endhighlight %}