package com.mashup

import com.mashup.github.GitHubRepository
import com.mashup.github.GitHubRepositories._
import com.mashup.twitter.TweetSummary
import io.circe.Encoder
import io.circe.generic.semiauto._

case class RepositoryTweetsSummary(repository: GitHubRepository, tweetsSummary: Seq[TweetSummary])

object RepositoryTweetsSummary {
  lazy implicit val repositoryTweetsSummaryEncoder: Encoder[RepositoryTweetsSummary] = deriveEncoder[RepositoryTweetsSummary]
}