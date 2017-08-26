package com.mashup.github

trait GitFailure

case class ClientGitFailure(statusCode: Int, message: String) extends GitFailure

case class ServerGitFailure(statusCode: Int, message: String) extends GitFailure

case class GenericGitFailure(message: String) extends GitFailure