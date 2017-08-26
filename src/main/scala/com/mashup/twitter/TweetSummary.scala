package com.mashup.twitter

import io.circe.Encoder
import io.circe.generic.semiauto._
import io.circe.syntax._
import org.joda.time.DateTime

case class TweetSummary(retweetCount: Long, text: String, user: String, createdAt: DateTime)

object TweetSummary {
  lazy implicit val dateTimeEncoder: Encoder[DateTime] = Encoder.instance(a => a.getMillis.asJson)
  lazy implicit val tweetSummaryEncoder: Encoder[TweetSummary] = deriveEncoder[TweetSummary]
}