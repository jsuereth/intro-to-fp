---
layout: default
title: ""
published: true
data:
  x: -750
  y: -1650
  rotate: -50
  z: 900
---

# Too much blocking #

* I have a <em>sequence</em> of projects 
    (<code class="scala"><span class="kt">Seq[Project]</span></code>)
* I have an <em>asynchronous operation</em> on a project 
    (<code class="scala"><span class="kt">Project =&gt; Future[Seq[PullRequests]]</span></code>)
* I <em>want</em> an asynchronous collective result 
    (<code class="scala"><span class="kt">Future[Seq[PullRequest]]</span></code>)
* Should <em>not block</em> for every element of a collection!