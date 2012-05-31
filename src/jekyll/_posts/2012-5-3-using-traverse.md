---
layout: default
title: ""
published: true
data:
  x: 1050
  y: -950
  rotate: 40
  z: -1200
---

# Traversing #

<div class="highlight"><pre><code class="scala"><span class="k">def</span> getStatistics(user<span class="kt">: User</span>)<span class="kt">: Future[Statistics]</span> = {
  <span class="k">val</span> projects<span class="kt">: Future[Seq[Project]]</span> = 
    api.projects(user)
  <span class="k">val</span> requests<span class="kt">: Future[Seq[Seq[PullRequests]]]</span> = 
    projects <em>flatMap</em> { projects =&gt; Future.<em>traverse</em>(projects)(api.pullrequests) }
  <span class="k">val</span> watchers<span class="kt">: Future[Seq[Seq[User]]]</span> = 
    projects <em>flatMap</em> { projects =&gt; Future.<em>traverse</em>(projects)(api.watchers) }    
  Statistics(user, reqs.<em>await.flatten</em>, watchers.<em>await.flatten</em>)
}
</code></pre></div>