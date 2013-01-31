package generic

import concurrent.Future
import concurrent.promise
import util.parsing.json.{JSON,JSONType,JSONObject,JSONArray}
import scala.util.control.NonFatal

object LiveGhApi extends GhApi[Future] {
  private final val githubUrl = "https://api.github.com"
  
    // THE BIG UGLY -> Generic grab JSON, parse with scala's json parser and
    // delegate extraction to
  private def rest[A](uri: String)(handleJson: Any => A): Future[A] = {
    val result = promise[A]
    import dispatch._
    Http(url(uri) OK as.String).option foreach {
      case Some(json) =>
        // TODO - Parse and fill out our result
        JSON.parseFull(json) match {
          case None        => result.failure(new RuntimeException("Unable to parse json response: " + json))
          case Some(value) => 
          try result.success(handleJson(value))
          catch {
            case NonFatal(e) => result.failure(e)
          }
        }
      case None => result.failure(new RuntimeException("Unable to read: " + uri))
    }
    result.future
  }
    
  def projects(user: String): Future[Seq[Project]] = 
    rest(s"${githubUrl}/users/${user}/repos") { 
      case data: Seq[Map[String,Any]] =>
        data map { json => Project(user, json("name").toString) }
    }
  def pullrequests(proj: Project): Future[Seq[PullRequest]] = 
    rest(s"${githubUrl}/repos/${proj.owner}/${proj.name}/pulls") { 
      case data: Seq[Map[String,Any]] =>
        data map { json => PullRequest(proj, json("number").toString) }
    }
  
  def collaborators(proj: Project): Future[Seq[Collaborator]] = 
    rest(s"${githubUrl}/repos/${proj.owner}/${proj.name}/collaborators") { 
      case data: Seq[Map[String, Any]] =>
        data map (json => Collaborator(json("login").toString))
    }
}