---
layout: default
title: ""
published: true
data:
  x: -50
  y: -2150
  z: 000
  rotate: 0
---

# More Asynch? #
<div class="highlight"><pre><code class="scala"><span class="k">def</span> getStatistics(user<span class="kt">: User</span>)<span class="kt">: <em>Future</em>[Statistics]</span> =
  api.projects(user) <em>map</em> { projects =&gt;
    <span class="k">var</span> reqs = Seq.empty[<span class="kt">PullRequest</span>]
    <span class="k">var</span> watchers = Seq.empty[<span class="kt">User</span>]
    <span class="k">for</span>(project <span class="k">&lt;-</span> projects) {
      reqs ++= api.pullreqs(project).<em>await</em>
      watchers ++= api.watchers(project).<em>await</em>
    }
    Statistics(user, reqs, watchers)
  }
</code></pre></div>