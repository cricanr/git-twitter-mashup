package com.mashup.twitter

import java.time.LocalDate
import com.danielasfregola.twitter4s.TwitterRestClient
import com.danielasfregola.twitter4s.entities._
import com.danielasfregola.twitter4s.entities.enums.ResultType
import org.mockito.ArgumentMatchers.{eq => mockitoEq}
import org.mockito.Mockito._
import org.scalactic.{Bad, Good}
import org.scalatest.mock.MockitoSugar
import org.scalatest.{MustMatchers, WordSpec}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.language.postfixOps

class TwitterClientTest extends WordSpec with MustMatchers with MockitoSugar {
  "The TwitterClient" when {
    val query = "reactive"
    val countOfTweetsToFetch = 10
    val tweetToFetchUntilDays = 1

    // TODO: possible improve, test mapping from Tweeter API entity to Tweet summary
    "calling getTweetsByQuery with a query" should {
      "return a list of tweet summary items" in {
        val twitterRestClientMock = mock[TwitterRestClient]
        val twitterClient = new TwitterClient(twitterRestClientMock)

        val ratedDataMock = mock[RatedData[StatusSearch]]
        val result = Future.successful(RatedData[StatusSearch](RateLimit(1, 1, 2),
          StatusSearch(List.empty, SearchMetadata(1, 1, "", None, "", "", 1, 2, ""))))

        when(twitterRestClientMock.searchTweet(query = query, count = countOfTweetsToFetch,
          include_entities = false, result_type = ResultType.Mixed, locale = Some("en"),
          until = Some(LocalDate.now().minusDays(tweetToFetchUntilDays)))).thenReturn(result)

        val resultOrFailureFuture = twitterClient.getTweetsByQuery(query, countOfTweetsToFetch, tweetToFetchUntilDays)

        val resultOrFailure = Await.result(resultOrFailureFuture, 1 second)
        resultOrFailure must be(Good(List.empty))
      }
    }

    "calling getTweetsByQuery with a query returning a failure" should {
      "return a list of tweet summary items" in {

        val twitterRestClientMock = mock[TwitterRestClient]
        val twitterClient = new TwitterClient(twitterRestClientMock)

        val ratedDataMock = mock[RatedData[StatusSearch]]
        val result = Future.failed(new Exception("ohh something broke!"))

        when(twitterRestClientMock.searchTweet(query = query, count = countOfTweetsToFetch,
          include_entities = false, result_type = ResultType.Mixed, locale = Some("en"),
          until = Some(LocalDate.now().minusDays(tweetToFetchUntilDays)))).thenReturn(result)

        val resultOrFailureFuture = twitterClient.getTweetsByQuery(query, countOfTweetsToFetch, tweetToFetchUntilDays)

        val resultOrFailure = Await.result(resultOrFailureFuture, 1 second)

        resultOrFailure must be(Bad(TwitterFailure("class java.lang.Exception: ohh something broke!")))
      }
    }
  }
}
