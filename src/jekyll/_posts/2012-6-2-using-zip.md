---
layout: default
title: ""
published: true
data:
  x: -50
  y: -2150
  rotate: -10
  z: -1400
---

# Using zip #

<div class="highlight"><pre><code class="scala"><span class="k">def</span> getStatistics(user<span class="kt">: User</span>)<span class="kt">: Future[Statistics]</span> = {
  <span class="k">val</span> projects<span class="kt">: Future[Seq[Project]]</span> = api.projects(user)
  <span class="k">val</span> requests<span class="kt">: Future[Seq[PullRequest]]</span> = 
    projects <em>flatMap</em> { projects =&gt; Future.<em>traverse</em>(projects)(api.pullrequests) } map (_.flatten)
  <span class="k">val</span> watchers<span class="kt">: Future[Seq[User]]</span> = 
    projects <em>flatMap</em> { projects =&gt; Future.<em>traverse</em>(projects)(api.watchers) } map (_.flatten)
  <span class="k">val</span> together<span class="kt">: Future[(Seq[PullRequest], Seq[User])]</span> =
    reqs <em>zip</em> watchers    
  together <em>map</em> { case (r, w) => Statistics(user, r, w) }
}
</code></pre></div>