package com.mashup.github

import io.circe.Decoder.Result

case class GitHubConfig(endpoint: String, timeout: Int)

case class GitHubRepository(name: String, url: String, description: String, score: Double)
case class GitHubRepositories(totalCount: Int, repositories: List[GitHubRepository])

object GitHubRepositories {
  import io.circe._
  import io.circe.generic.semiauto._
  import io.circe.parser._

  implicit val decodeGitHubRepository: Decoder[GitHubRepository] = new Decoder[GitHubRepository] {
    final def apply(c: HCursor): Decoder.Result[GitHubRepository] =
      for {
        name <- c.downField("name").as[String]
        url <- c.downField("url").as[String]
        description <- c.downField("description").as[String]
        score <- c.downField("score").as[Double]
      } yield GitHubRepository(name, url, description, score)
  }

  implicit val decodeGitHubRepositories: Decoder[GitHubRepositories] = new Decoder[GitHubRepositories] {
    final def apply(c: HCursor): Decoder.Result[GitHubRepositories] =
      for {
        totalCount <- c.downField("total_count").as[Int]
        repositories <- c.downField("items").as[List[GitHubRepository]]
      } yield GitHubRepositories(totalCount, repositories)
  }

  implicit val gitHubRepositoryEncoder: Encoder[GitHubRepository] = deriveEncoder
  implicit val gitHubRepositoriesEncoder: Encoder[GitHubRepositories] = deriveEncoder

  def jsonParse(json: String): Result[GitHubRepositories] = {
    parse(json).getOrElse(Json.Null).as[GitHubRepositories]
  }
}