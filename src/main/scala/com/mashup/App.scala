package com.mashup

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.mashup.github.{GitHubClient, GitHubRepositories}
import com.mashup.twitter.{TweetSummary, TwitterClient}
import org.scalactic.{Bad, Good}
import play.api.libs.ws.ahc.StandaloneAhcWSClient
import io.circe.syntax._
import scala.concurrent.ExecutionContext.Implicits.global

object App {
  private val query = "reactive"

  def main(args: Array[String]) {
    implicit val system: ActorSystem = ActorSystem()
    system.registerOnTermination {
      System.exit(0)
    }
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    val wsClient = StandaloneAhcWSClient()

    val gitHubClient = new GitHubClient(wsClient)
    val twitterClient = new TwitterClient()

    val gitHubConfig = Configuration.getGitHubConfig
    val reposOrFailureFuture = gitHubClient.getRepositoriesByKeyword(query, gitHubConfig.endpoint, gitHubConfig.timeout)

    reposOrFailureFuture.foreach {
      reposOrFailure =>
        reposOrFailure.map {
          repos: GitHubRepositories =>
            repos.repositories.foreach {
              repository =>
                val tweetsSummaryOrFailureFuture = twitterClient.getTweetsByQuery(repository.name)
                tweetsSummaryOrFailureFuture.map {
                  case Good(tweetsSummary: List[TweetSummary]) =>
                    val repositoryTweetsSummary = new RepositoryTweetsSummary(repository, tweetsSummary)
                    val repositoryTweetsSummaryJson = repositoryTweetsSummary.asJson
                    println(repositoryTweetsSummaryJson)
                  case Bad(failure) =>
                    println(s"Failed calling Twitter API to fetch tweets for repository: ${repository.name} " +
                      s"with failure: ${failure.getClass}")
                }.recover {
                  case failure: Throwable => println("s\"Failed calling Twitter API to fetch tweets for repository: ${repository.name} \" +\n " +
                    s"with failure: ${failure.getClass}: ${failure.getMessage}")
                }
            }
        }
    }

    val futTweetsOrFailure = twitterClient.getTweetsByQuery(query)

  }
}
