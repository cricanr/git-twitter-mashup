package com.mashup.github

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import org.mockito.ArgumentMatchers.{eq => mockitoEq, _}
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{MustMatchers, WordSpec}
import play.api.libs.ws.ahc.{StandaloneAhcWSClient, StandaloneAhcWSResponse}
import org.mockito.Mockito._
import org.scalactic.{Bad, Good}
import org.scalatest.concurrent.Eventually
import play.api.libs.ws.StandaloneWSRequest

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Failure

class GitHubClientTest extends WordSpec with MustMatchers with MockitoSugar with Eventually {
  "The GitHub API Client" when {
    "searching repositories by keyword" should {
      "return a list of github repositories" in {
        implicit val executionContextMock = mock[ExecutionContext]
        implicit val system: ActorSystem = ActorSystem()
        implicit val materializer: ActorMaterializer = ActorMaterializer()

        val standaloneWSClientMock = mock[StandaloneAhcWSClient]
        val requestMock = mock[StandaloneWSRequest]
        val responseMock = mock[Future[StandaloneAhcWSResponse]]

        // TODO: finish me
        //when(requestMock.get()).thenReturn(Future.successful(Good(GitHubRepositories(1, List(GitHubRepository("n", ))))))

        val gitHubClient = new GitHubClient(standaloneWSClientMock)

        val failure = gitHubClient.getRepositoriesByKeyword(requestMock, "reactive")

        failure must be(Bad(GeneralGitFailure("I screwed up!!")))
      }
    }
  }
}