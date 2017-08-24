package com.apis.gitTwitterMashup.git

import java.util.concurrent.TimeUnit

import org.scalactic.{Bad, Good}
import play.api.libs.ws.StandaloneWSClient

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.Duration
import scala.language.postfixOps

case class GitHubRepository(name: String, url: String, description: String, score: Double, totalCount: Int)
case class Failure(message: String) extends Throwable

class GitHubClient(wsClient: StandaloneWSClient)(implicit executionContext: ExecutionContext) {
  def getRepositoriesByKeyword(keyword: String, uri: String, timeout: Int) = {
    val request = wsClient.url(uri)
//      .withHttpHeaders(("Accept" -> "application/json"))
      .withRequestTimeout(Duration(timeout, TimeUnit.SECONDS))
    val futureResponse = request.get()
    futureResponse.map {
      wsResponse =>
        (wsResponse.status, wsResponse.body) match {
          case (responseStatusCode, _) if responseStatusCode >= 200 && responseStatusCode < 300 =>
            println(s"github search results: ${wsResponse.body}")
            Good(GitHubRepository(wsResponse.body, "desc", "", 21, 10))
          case (clientFailureStatusCode, body) =>
            // TODO
            Bad(Failure("clientFailure.... "))
        }
    }.recover {
      case e: Throwable =>
        println("error")
        Bad(Failure(s"failed call to get GitHub repositories containing keyword: $keyword"))
    }
  }
}


object GitHubRepository {
  import cats.syntax.either._
  import io.circe._
  import io.circe.parser._

  def jsonParse(json: String): GitHubRepository = {
    parse(json).getOrElse(Json.Null).as[GitHubRepository].right.get
  }

  implicit val decodeGitHubRepository: Decoder[GitHubRepository] = new Decoder[GitHubRepository] {
    final def apply(c: HCursor): Decoder.Result[GitHubRepository] =
      for {
        totalCount <- c.downField("total_count").as[Int]
        name <- c.downField("items").downArray.downField("name").as[String]
        url <- c.downField("items").downArray.downField("url").as[String]
        description <- c.downField("items").downArray.downField("description").as[String]
        score <- c.downField("items").downArray.downField("score").as[Double]
      } yield {
        new GitHubRepository(name, url, description, score, totalCount)
      }
  }
}