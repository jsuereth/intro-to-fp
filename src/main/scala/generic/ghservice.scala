package generic

import concurrent.Future
import concurrent.promise
import util.parsing.json.{JSON,JSONType,JSONObject,JSONArray}
import scala.util.control.NonFatal

case class AuthApp(name: String, url: String)
case class Authorization(id: String, token: String, app: AuthApp, note: Option[String])

object Authenticate {
  import dispatch._
  private[this] val authorizations = 
    url("https://api.github.com/authorizations").setHeader("User-Agent", "github.com/jsuereth/intro-to-fp")

  val authScopes = """{
 "scopes": [ 
   "repo"
 ],
 "note": "demo API Access"
}"""

  /** This method looks for a previous GH authorization for this API and retrieves it, or
   * creates a new one.
   */
  def authenticate(user: String, pw: String): Authorization = {
    val previousAuth: Option[Authorization] =
      (getAuthentications(user,pw) filter (_.note == Some("demo API Access"))).headOption
    previousAuth foreach (auth => println("Found previous github auth for: " + auth.app))
    previousAuth getOrElse makeAuthentication(user, pw)
  }

 private def parseAuthorization(json: Any): Authorization = {
   val map = json.asInstanceOf[Map[String, Any]]
   val appMap = map("app").asInstanceOf[Map[String,Any]]
   Authorization(
       map("id").toString,
       map("token").toString,
       AuthApp(appMap("name").toString, appMap("url").toString),
       map get "note" filterNot (_ == null) map (_.toString))
 }
  
  def makeAuthentication(user: String, pw: String): Authorization = {
    val string = Http(authorizations.POST.as_!(user, pw) << authScopes OK as.String)()
    System.out.println("Creating new authentication token.")
    parseAuthorization(JSON.parseFull(string).get)
  }
    //Http(authorizations.POST.as_!(user, pw) << authScopes >- parseJsonTo[Authorization])

  def getAuthentications(user: String, pw: String): List[Authorization] = {
    val string = Http(authorizations.as_!(user, pw) OK as.String)()
    val list = JSON.parseFull(string).get
    list match {
      case list: Seq[Any] => (list map parseAuthorization)(collection.breakOut)
      case map: Map[String, Any] => List(parseAuthorization(map))
    }
  }

  def deleteAuthentication(auth: Authorization, user: String, pw: String): Unit =
    Http((authorizations / auth.id).DELETE.as_!(user,pw))()

  def deleteAuthentications(user: String, pw: String): Unit =
     getAuthentications(user, pw) foreach { a => 
       deleteAuthentication(a, user, pw) 
     }
}


object LiveGhApi extends GhApi[Future] {
  private final val githubUrl = "https://api.github.com"

  lazy val authorization: Authorization = {
    val props = new java.util.Properties
    val in = new java.io.FileInputStream(new java.io.File("github.properties"))
    try props.load(in)
    finally in.close()
    System.err.println("Authenticating user: " + props.getProperty("github.user"))
    Authenticate.authenticate(props.getProperty("github.user"), props.getProperty("github.pw"))
  }
  
    // THE BIG UGLY -> Generic grab JSON, parse with scala's json parser and
    // delegate extraction to
  private def rest[A](uri: String)(handleJson: Any => A): Future[A] = {
    val result = promise[A]
    import dispatch._
    Http(url(uri).setHeader("User-Agent", "github.com/jsuereth/intro-to-fp").setHeader("Authorization", s"token ${authorization.token}") OK as.String) foreach {
      json =>
        // TODO - Parse and fill out our result
        JSON.parseFull(json) match {
          case None        => result.failure(new RuntimeException("Unable to parse json response: " + json))
          case Some(value) => 
          try result.success(handleJson(value))
          catch {
            case NonFatal(e) => result.failure(e)
          }
        }
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