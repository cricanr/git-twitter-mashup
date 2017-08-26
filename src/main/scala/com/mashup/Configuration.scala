package com.mashup

import com.mashup.github.GitHubConfig
import com.mashup.twitter.TwitterConfig
import com.typesafe.config.{Config, ConfigFactory}

import scala.util.Try

object Configuration {
  val conf: Config = ConfigFactory.load

  def getTwitterConfig: TwitterConfig = {
    val getTweetsUntilDays: Int = Try(conf.getInt("get-tweets-until-days")).getOrElse(1)
    val tweetsToFetchCount: Int = Try(conf.getInt("tweets-to-fetch-count")).getOrElse(10)

    TwitterConfig(getTweetsUntilDays, tweetsToFetchCount)
  }

  def getGitHubConfig: GitHubConfig = {
    val githubApiEndpoint = Try(conf.getString("github-api-endpoint")).getOrElse("Invalid github api endpoint provided. Please check config file.")
    val gitHubApiTimeout = Try(conf.getInt("github-api-timeout")).getOrElse(300)

    GitHubConfig(githubApiEndpoint, gitHubApiTimeout)
  }
}