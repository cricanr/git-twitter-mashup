package com.mashup.github

trait GitFailure

case class ClientGitFailure(statusCode: Int, message: String) extends GitFailure

case class GeneralGitFailure(message: String) extends GitFailure