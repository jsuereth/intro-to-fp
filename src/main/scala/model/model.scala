package model


case class Project(owner: String, name: String)
case class PullRequest(project: Project, id: String, contributor: String, merged: Boolean)
case class Collaborator(name: String)


case class ProjectStatistics(
    project: Project,
    collaborators: Seq[Collaborator],
    pullreqs: Seq[PullRequest]) {
  
  val mergedCount = pullreqs.filter(_.merged).size
  val mergePercent = (mergedCount / pullreqs.size.toDouble) * 100.0;
  
  val outsidePrs = pullreqs filterNot { pr =>
    collaborators exists (c => c.name == pr.contributor)
  }
  val outsideMerged = outsidePrs.filter(_.merged).size
  val outsideMergePercent = (outsideMerged / pullreqs.size.toDouble) * 100.0;
  
  override def toString =
    f"""|\t---- Project ${project.name} Statistics---
        |\t  Pull requests:         ${pullreqs.length}
        |\t  %% Merged:              ${mergePercent}%02.2f
        |\t  %% Merged from Outside: ${outsideMergePercent}%02.2f""".stripMargin
}

case class Statistics(
    user: String,
    projects: Seq[ProjectStatistics]) {
  
  // Make sure they've had activity.
  val interestingProjects = projects filter (_.pullreqs.size > 5)
  
  override def toString =
    s"""|--- User ${user} Statitiscs
        |${interestingProjects mkString "\n"}"""
}