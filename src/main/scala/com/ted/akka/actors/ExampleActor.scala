package com.ted.akka.actors

import akka.actor.{Actor, Props}
import com.ted.akka.actors.ExampleActor.GetProductsUrl
import com.ted.akka.extensions.SettingsActor

class ExampleActor extends Actor with SettingsActor {
  val protocol = settings.productService.protocol
  val host = settings.productService.host
  val port = settings.productService.port
  val uri = settings.productService.products.uri

  def receive = {
    case GetProductsUrl => sender() ! s"$protocol$host:$port$uri"
  }
}

object ExampleActor {
  def props() = Props(new ExampleActor)

  case object GetProductsUrl
}