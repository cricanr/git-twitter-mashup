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

    val sc = new java.util.Scanner(System.in)

    println(s"\nWelcome! This console app will next get 10 github projects containing the keyword: ${query}." +
      s"\nAfter that for each repository found it will take from Twitter API tweets older as " +
      s"\n${Configuration.getTweetsUntilDays} containing the name of these repos. The resulting summary of " +
      s"\nrepos with attached tweets summary will be printed out to the console in JSON format! Enjoy!" +
      s"\n-----------------------------------------------------------------------------------------------" +
      s"\nIn order to quit just press 'quit'" +
      s"\n-----------------------------------------------------------------------------------------------" +
      s"\n")

    mashup.outputMergeResults(query)

    while(true)
      if (sc.next().toLowerCase == "quit".toLowerCase)
        System.exit(1)
  }
}
