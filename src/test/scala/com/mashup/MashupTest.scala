package com.mashup

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.mashup.github.{ClientGitFailure, GitHubClient, GitHubConfig}
import com.mashup.twitter.{TwitterClient, TwitterConfig}
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.{eq => mockitoEq}
import org.mockito.Mockito._
import org.scalactic.Bad
import org.scalatest.WordSpec
import org.scalatest.mockito.MockitoSugar
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.{ExecutionContext, Future}

class MashupTest extends WordSpec with MockitoSugar {
  "" when {
    "" should {
      "" ignore {
        // TODO: finish me
        implicit val executionContextMock = mock[ExecutionContext]
        implicit val system: ActorSystem = ActorSystem()
        implicit val materializer: ActorMaterializer = ActorMaterializer()
        implicit val gitHubConfigMock = mock[GitHubConfig]
        implicit val twitterConfigMock = mock[TwitterConfig]

        val standaloneWSClientMock = mock[StandaloneAhcWSClient]
        val gitHubClientMock = mock[GitHubClient]

        when(gitHubClientMock.getRepositoriesByKeyword(ArgumentMatchers.any(), ArgumentMatchers.any()))
          .thenReturn(Future.successful(Bad(ClientGitFailure(400, "client failure"))))
        val twitterClientMock = mock[TwitterClient]

        val mashup = new Mashup(gitHubClientMock, twitterClientMock)
        mashup.outputMergeResults("reactive")

        verify(println(""))
      }
    }
  }
}
