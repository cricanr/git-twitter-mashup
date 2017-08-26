package com.mashup

import com.mashup.github.GitHubConfig
import com.typesafe.config.{Config, ConfigFactory}

import scala.util.Try

object Configuration {
  val conf: Config = ConfigFactory.load

  val getTweetsUntilDays: Int = Try(conf.getInt("get-tweets-until-days")).getOrElse(1)

  def getGitHubConfig: GitHubConfig = {
    val githubApiEndpoint = Try(conf.getString("github-api-endpoint")).getOrElse("Invalid github api endpoint provided. Please check config file.")
    val gitHubApiTimeout = Try(conf.getInt("github-api-timeout")).getOrElse(300)

    GitHubConfig(githubApiEndpoint, gitHubApiTimeout)
  }
}