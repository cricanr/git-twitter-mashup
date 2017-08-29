package com.mashup

import com.mashup.RepositoryTweetsSummary._
import com.mashup.github._
import com.mashup.twitter.{TweetSummary, TwitterClient, TwitterConfig, TwitterFailure}
import io.circe.syntax._
import org.scalactic.{Bad, Good, Or}
import scala.concurrent.{ExecutionContext, Future}

class Mashup(gitHubClient: GitHubClient, twitterClient: TwitterClient) {
  def outputMergedResults(query: String)
                         (implicit gitHubConfig: GitHubConfig,
                         twitterConfig: TwitterConfig,
                         executionContext: ExecutionContext): Unit = {
    val reposOrFailureFuture = gitHubClient.getRepositoriesByKeyword(
      gitHubClient.buildRequest(gitHubConfig.endpoint, gitHubConfig.timeout), query)

    reposOrFailureFuture.map {
      case Good(repos) =>
        repos.repositories.map {
          repository =>
            val tweetsSummaryOrFailureFuture = twitterClient.getTweetsByQuery(repository.name, twitterConfig.tweetsToFetchCount, twitterConfig.tweetsUntilDays)
            createJson(repository, query, tweetsSummaryOrFailureFuture)
        }
      case Bad(clientGitFailure: ClientGitFailure) =>
        println(s"Client failure calling Git API to fetch repositories containing: $query + \n " +
          s"with failure: ${clientGitFailure.statusCode}: ${clientGitFailure.message}")
      case Bad(genericGitFailure: ServerGitFailure) =>
        println(s"Failed calling Git API to fetch repositories containing: $query +\n " +
          s"with failure: ${genericGitFailure.message}")
      case _ =>
    }
  }

  private def createJson(repository: GitHubRepository, query: String,
                         tweetsSummaryOrFailureFuture: Future[Or[List[TweetSummary], TwitterFailure]])
                        (implicit executionContext: ExecutionContext) = {
    tweetsSummaryOrFailureFuture.map {
      case Good(tweetsSummary: List[TweetSummary]) =>
        val repositoryTweetsSummary = RepositoryTweetsSummary(repository, tweetsSummary)
        val repositoryTweetsJson = repositoryTweetsSummary.asJson.toString
        println(s"Repositories containing keyword: $query along with their tweets found are: \n " +
          s"$repositoryTweetsJson")
      case Bad(failure) =>
        println(s"Failed calling Twitter API to fetch tweets for repository: ${repository.name} " +
          s"with failure: ${failure.getClass}")
    }.failed.map {
      failure =>
        println("s\"Failed calling Twitter API to fetch tweets for repository: ${repository.name} \" +\n " +
          s"with failure: ${failure.getClass}: ${failure.getMessage}")
    }
  }
}
