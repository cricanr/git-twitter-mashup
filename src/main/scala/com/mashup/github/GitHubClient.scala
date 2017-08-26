package com.mashup.github

import java.util.concurrent.TimeUnit

import org.scalactic.{Bad, Good, Or}
import play.api.libs.ws.StandaloneWSClient

import scala.concurrent.duration.Duration
import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class GitHubClient(wsClient: StandaloneWSClient)(implicit executionContext: ExecutionContext) {
  def getRepositoriesByKeyword(keyword: String, uri: String, timeout: Int):
  Future[Or[GitHubRepositories, GitFailure]] = {
    val request = wsClient.url(uri)
      .withRequestTimeout(Duration(timeout, TimeUnit.SECONDS))
    val futureResponse = request.get()
    futureResponse.map {
      wsResponse =>
        (wsResponse.status, wsResponse.body) match {
          case (responseStatusCode, _) if responseStatusCode >= 200 && responseStatusCode < 300 =>
            val reactiveRepositoriesParseRes = GitHubRepositories.jsonParse(wsResponse.body)
            reactiveRepositoriesParseRes match {
              case Right(repos) =>
                Good(repos)
              case Left(failure) =>
                println(s"There was a problem decoding the github repositories found by searching for $keyword keyword. See more details: ${failure.getClass}:${failure.message}")
                Bad(GeneralGitFailure(s"There was a problem decoding the github repositories found by searching for $keyword keyword. See more details: ${failure.getClass}:${failure.message}"))
            }
          case (clientFailureStatusCode, body) =>
            Bad(ClientGitFailure(clientFailureStatusCode, body))
          case _ =>
            val errorMessage = "Internal server error"
            println(errorMessage)
            Bad(GeneralGitFailure(errorMessage))
        }
    }.recover {
      case _ =>
        val errorMessage = s"failed call to get GitHub repositories containing keyword: $keyword"
        println(errorMessage)
        Bad(GeneralGitFailure(errorMessage))
    }
  }
}

