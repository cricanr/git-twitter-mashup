package com.mashup.github

import java.util.concurrent.TimeUnit

import org.scalactic.{Bad, Good, Or}
import play.api.libs.ws.{StandaloneWSClient, StandaloneWSRequest, StandaloneWSResponse}

import scala.concurrent.duration.Duration
import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class GitHubClient(wsClient: StandaloneWSClient)(implicit executionContext: ExecutionContext) {
  def getRepositoriesByKeyword(request: StandaloneWSRequest, keyword: String):
    Future[Or[GitHubRepositories, GitFailure]] = {
    val futureResponse = request.get()

    futureResponse.map(resp =>
      parseResponse(keyword, resp))
      .recover {
        case f: Throwable =>
          val errorMessage = s"failed call to get GitHub repositories containing keyword: $keyword"
          println(errorMessage)
          Bad(GenericGitFailure(errorMessage))
    }
  }

  def buildRequest(uri: String, timeout: Int) = {
    wsClient.url(uri)
      .withRequestTimeout(Duration(timeout, TimeUnit.SECONDS))
  }

  def parseResponse(keyword: String, wsResponse: StandaloneWSResponse): Or[GitHubRepositories, GitFailure] = {
    (wsResponse.status, wsResponse.body) match {
      case (responseStatusCode, _) if responseStatusCode >= 200 && responseStatusCode < 300 =>
        val reactiveRepositoriesParseRes = GitHubRepositories.jsonParse(wsResponse.body)
        reactiveRepositoriesParseRes match {
          case Right(repos) =>
            Good(repos)
          case Left(failure) =>
            println(s"There was a problem decoding the github repositories found by searching for $keyword keyword. See more details: ${failure.getClass}:${failure.message}")
            Bad(GenericGitFailure(s"There was a problem decoding the github repositories found by searching for $keyword keyword. See more details: ${failure.getClass}:${failure.message}"))
        }
      case (clientFailureStatusCode, body) if clientFailureStatusCode >= 400 && clientFailureStatusCode < 500 =>
        Bad(ClientGitFailure(clientFailureStatusCode, body))
      case (serverFailureStatusCode, body) =>
        println(body)
        Bad(ServerGitFailure(serverFailureStatusCode, body))
    }
  }
}

