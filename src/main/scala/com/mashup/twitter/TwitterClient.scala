package com.mashup.twitter

import java.time.LocalDate
import com.danielasfregola.twitter4s.entities.enums.ResultType
import com.mashup.Configuration._
import org.joda.time.DateTime
import org.scalactic.{Bad, Good, Or}
import scala.concurrent.{ExecutionContext, Future}

class TwitterClient {
  import com.danielasfregola.twitter4s.TwitterRestClient

  private val client = TwitterRestClient()

  def getTweetsByQuery(query: String)(implicit executionContext: ExecutionContext): Future[List[TweetSummary] Or TwitterFailure] = {
    val tweetsFuture = client.searchTweet(query = query, count = 10, include_entities = false,
      result_type = ResultType.Mixed, locale = Some("en"),
      until = Some(LocalDate.now().minusDays(getTweetsUntilDays)))

    tweetsFuture.map{ tweets =>
      val tweetsSummary = tweets.data.statuses.map { tweet =>
      TweetSummary(tweet.retweet_count, tweet.text, tweet.user.map(_.name).getOrElse(""), new DateTime(tweet.created_at))}
      Good(tweetsSummary)
      }.recover {
      case f: Throwable => Bad(TwitterFailure(s"${f.getClass}: ${f.getMessage}"))
    }
  }
}
