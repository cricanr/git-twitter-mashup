package com.apis.gitTwitterMashup.twitter


import java.time.LocalDate

import com.danielasfregola.twitter4s.entities.Tweet
import com.danielasfregola.twitter4s.entities.enums.ResultType

import scala.concurrent.{ExecutionContext, Future}

class TwitterClient {
  import com.danielasfregola.twitter4s.TwitterRestClient

  private val client = TwitterRestClient()

  def getTweetsByQuery(query: String)(implicit executionContext: ExecutionContext): Future[List[Tweet]] = {
    // TODO: parametrize get tweets until with config
    val tweetsFuture = client.searchTweet(query, 10, false, ResultType.Mixed, None, None, None, Some(LocalDate.now().minusDays(3)))

    tweetsFuture.map(tweets => tweets.data.statuses)
  }
}
