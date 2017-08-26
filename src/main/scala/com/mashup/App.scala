package com.mashup

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.mashup.github.{GitHubClient, GitHubConfig}
import com.mashup.twitter.TwitterClient
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.ExecutionContext.Implicits.global

object App {
  private val query = "reactive"

  def main(args: Array[String]) {
    implicit val system: ActorSystem = ActorSystem()
    implicit val materializer: ActorMaterializer = ActorMaterializer()
    implicit val gitHubConfig: GitHubConfig = Configuration.getGitHubConfig

    system.registerOnTermination {
      System.exit(0)
    }

    val wsClient = StandaloneAhcWSClient()

    val gitHubClient = new GitHubClient(wsClient)
    val twitterClient = new TwitterClient()

    val mashup = new Mashup(gitHubClient, twitterClient)
    mashup.outputMergeResults(query)

//    val reposOrFailureFuture = gitHubClient.getRepositoriesByKeyword(query, gitHubConfig.endpoint, gitHubConfig.timeout)
//
//    reposOrFailureFuture.foreach {
//      reposOrFailure =>
//        reposOrFailure.map {
//          repos: GitHubRepositories =>
//            repos.repositories.foreach {
//              repository =>
//                val tweetsSummaryOrFailureFuture = twitterClient.getTweetsByQuery(repository.name)
//                tweetsSummaryOrFailureFuture.map {
//                  case Good(tweetsSummary: List[TweetSummary]) =>
//                    val repositoryTweetsSummary = new RepositoryTweetsSummary(repository, tweetsSummary)
//                    val repositoryTweetsSummaryJson = repositoryTweetsSummary.asJson
//                    println(repositoryTweetsSummaryJson)
//                  case Bad(failure) =>
//                    println(s"Failed calling Twitter API to fetch tweets for repository: ${repository.name} " +
//                      s"with failure: ${failure.getClass}")
//                }.failed.foreach {
//                  failure: Throwable =>
//                    println("s\"Failed calling Twitter API to fetch tweets for repository: ${repository.name} \" +\n " +
//                      s"with failure: ${failure.getClass}: ${failure.getMessage}")
//                }
//            }
//        }
//    }
  }
}
