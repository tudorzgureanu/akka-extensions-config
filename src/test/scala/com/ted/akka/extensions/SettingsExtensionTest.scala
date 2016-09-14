package com.ted.akka.extensions

import akka.actor.ActorSystem
import akka.testkit.TestKit
import org.scalatest.{BeforeAndAfterAll, FunSuiteLike, Matchers, WordSpecLike}


class SettingsExtensionTest
  extends TestKit(ActorSystem("SettingsExtensionTest"))
    with FunSuiteLike
    with BeforeAndAfterAll
    with Matchers {

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  val settings = SettingsExtension(system)

  test("SettingsExtension to return the correct config values for service") {
    val service = settings.service
    service.name shouldBe "product-consumer"
  }

  test("SettingsExtension to return the correct config values for product-service") {
    val productService = settings.productService
    productService.protocol shouldBe "http"
    productService.host shouldBe "127.0.0.0"
    productService.port shouldBe "8080"

    productService.products.uri shouldBe "/products"
  }

}
