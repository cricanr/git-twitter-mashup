package com.mashup

import com.mashup.RepositoryTweetsSummary._
import com.mashup.github._
import com.mashup.twitter.{TweetSummary, TwitterClient, TwitterConfig}
import io.circe.syntax._
import org.scalactic.{Bad, Good}

import scala.concurrent.ExecutionContext

class Mashup(gitHubClient: GitHubClient, twitterClient: TwitterClient) {
  def outputMergeResults(query: String)
           (implicit gitHubConfig: GitHubConfig,
            twitterConfig: TwitterConfig,
            executionContext: ExecutionContext): Unit = {
    val reposOrFailureFuture = gitHubClient.getRepositoriesByKeyword(query, gitHubConfig.endpoint, gitHubConfig.timeout)

    reposOrFailureFuture.foreach {
      case Good(repos) =>
        repos.repositories.map {
          repository =>
            val tweetsOrFailureFuture = twitterClient.getTweetsByQuery(repository.name, twitterConfig.tweetsToFetchCount)
            tweetsOrFailureFuture.map {
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
      case Bad(clientGitFailure: ClientGitFailure) =>
        println(s"Client failure calling Git API to fetch repositories containing: $query + \n " +
          s"with failure: ${clientGitFailure.statusCode}: ${clientGitFailure.message}")
      case Bad(genericGitFailure: GeneralGitFailure) =>
        println(s"Failed calling Git API to fetch repositories containing: $query +\n " +
          s"with failure: ${genericGitFailure.message}")
      case _ =>
    }
  }
}
