package com.mashup

import com.danielasfregola.twitter4s.entities.Tweet
import com.mashup.github.GitHubRepository

case class RepositoryTweets(repository: GitHubRepository, tweets: Seq[Tweet])
