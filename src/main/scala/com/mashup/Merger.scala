//package com.mashup
//
//import com.danielasfregola.twitter4s.entities.{HashTag, Tweet}
//import com.mashup.RepositoryTweetsSummary._
//import com.mashup.github.{GitHubClient, GitHubConfig, GitHubRepositories}
//import com.mashup.twitter.{TweetSummary, TwitterClient}
//import io.circe.syntax._
//import org.scalactic.{Bad, Good}
//import com.mashup.twitter.TweetSummary._
//
//import scala.concurrent.ExecutionContext
//
//class Merger(gitHubClient: GitHubClient, twitterClient: TwitterClient) {
//  def merge(query: String)
//           (implicit gitHubConfig: GitHubConfig, executionContext: ExecutionContext): List[RepositoryTweetsSummary] = {
//    val reposOrFailureFuture = gitHubClient.getRepositoriesByKeyword(query, gitHubConfig.endpoint, gitHubConfig.timeout)
//
//    val r =reposOrFailureFuture.map {
//      reposOrFailure =>
//        reposOrFailure.map {
//          repos: GitHubRepositories =>
//            repos.repositories.foreach {
//              repository =>
//                val tweetsOrFailureFuture = twitterClient.getTweetsByQuery(repository.name)
//                tweetsOrFailureFuture.map {
//                  case Good(tweets: List[TweetSummary]) =>
//                    val repositoryTweets = RepositoryTweetsSummary(repository, tweets)
////                    repositoryTweets.asJson
////                    val repositoryTweetsJson = repositoryTweets.asJson
////                    println(s"Repositories containing keyword: $query along with their tweets found are: $repositoryTweetsJson")
//                  case Bad(failure) =>
//                    println(s"Failed calling Twitter API to fetch tweets for repository: ${repository.name} " +
//                      s"with failure: ${failure.getClass}")
//
//                }.onFailure {
//                  case failure: Throwable =>
//                    println("s\"Failed calling Twitter API to fetch tweets for repository: ${repository.name} \" +\n " +
//                    s"with failure: ${failure.getClass}: ${failure.getMessage}")
//                }
//            }
//        }
//    }
//
//    ???
//  }
//
//  def getTopHashtags(tweets: Seq[Tweet], n: Int = 10): Seq[(String, Int)] = {
//    val hashtags: Seq[Seq[HashTag]] = tweets.map { tweet =>
//      tweet.entities.map(_.hashtags).getOrElse(Seq.empty)
//    }
//    val hashtagTexts: Seq[String] = hashtags.flatten.map(_.text.toLowerCase)
//    val hashtagFrequencies: Map[String, Int] = hashtagTexts.groupBy(identity).mapValues(_.size)
//    hashtagFrequencies.toSeq.sortBy { case (entity, frequency) => -frequency }.take(n)
//  }
//}
