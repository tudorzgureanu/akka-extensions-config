package com.ted.akka.actors

import akka.actor.Actor
import com.ted.akka.extensions.SettingsActor

class ProductsActor extends Actor with SettingsActor {
  val protocol = settings.productService.protocol
  val host = settings.productService.host
  val port = settings.productService.port
  val uri = settings.productService.products.uri

  def receive = Actor.emptyBehavior
}