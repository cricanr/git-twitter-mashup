package com.mashup

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.danielasfregola.twitter4s.TwitterRestClient
import com.mashup.github.{GitHubClient, GitHubConfig}
import com.mashup.twitter.{TwitterClient, TwitterConfig}
import play.api.libs.ws.ahc.StandaloneAhcWSClient
import scala.concurrent.ExecutionContext.Implicits.global

object App {
  private val query = "reactive"

  def main(args: Array[String]) {
    implicit val system: ActorSystem = ActorSystem()
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    implicit val gitHubConfig: GitHubConfig = Configuration.getGitHubConfig
    implicit val twitterConfig: TwitterConfig = Configuration.getTwitterConfig

    system.registerOnTermination {
      System.exit(0)
    }

    val wsClient = StandaloneAhcWSClient()
    val twitterRestClient = TwitterRestClient()
    val gitHubClient = new GitHubClient(wsClient)
    val twitterClient = new TwitterClient(twitterRestClient)

    val mashup = new Mashup(gitHubClient, twitterClient)

    val sc = new java.util.Scanner(System.in)

    println(s"\nWelcome! This console app will next get 10 github projects containing the keyword: ${query}." +
      s"\nAfter that for each repository found it will take from Twitter API tweets older as " +
      s"\n${twitterConfig.tweetsUntilDays} containing the name of these repos. The resulting summary of " +
      s"\nrepos with attached tweets summary will be printed out to the console in JSON format! Enjoy!" +
      s"\n-----------------------------------------------------------------------------------------------" +
      s"\nIn order to quit just press 'quit'" +
      s"\n-----------------------------------------------------------------------------------------------" +
      s"\n")

    mashup.outputMergedResults(query)

    while (true)
      if (sc.next().toLowerCase == "quit".toLowerCase)
        System.exit(1)
  }
}
