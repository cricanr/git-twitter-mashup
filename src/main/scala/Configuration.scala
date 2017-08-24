package com.apis.gitTwitterMashup

import com.typesafe.config.{Config, ConfigFactory}

import scala.util.Try

case class GitHubConfig(endpoint: String, timeout: Int)

object Configuration {
  val conf: Config = ConfigFactory.load

  def getGitHubConfig: GitHubConfig = {
    val githubApiEndpoint = Try(conf.getString("github-api-endpoint")).getOrElse("Invalid github api endpoint provided. Please check config file.")
    val gitHubApiTimeout = Try(conf.getInt("github-api-timeout")).getOrElse(300)

    GitHubConfig(githubApiEndpoint, gitHubApiTimeout)
  }
}