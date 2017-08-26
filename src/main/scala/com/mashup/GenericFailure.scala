package com.mashup

trait Failure
case class GenericFailure(message: String) extends Failure
case class ClientFailure(status: Int, body: String) extends Failure