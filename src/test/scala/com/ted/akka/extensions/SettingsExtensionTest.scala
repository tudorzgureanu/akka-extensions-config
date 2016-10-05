package com.ted.akka.extensions

import akka.actor.ActorSystem
import akka.testkit.TestKit
import org.scalatest.{BeforeAndAfterAll, FunSuiteLike, Matchers}

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

  test("SettingsExtension to return the correct config values for kafka.producer") {
    val kakfaProducer = settings.kafka.producer

    kakfaProducer.bootstrapServers shouldBe "http"
    kakfaProducer.acks shouldBe "127.0.0.0"
    kakfaProducer.batchSize shouldBe 8080
    kakfaProducer.bufferMemory shouldBe 8080
    kakfaProducer.retries shouldBe 8080
    kakfaProducer.lingerMs shouldBe 8080
  }

  test("SettingsExtension to return the correct config values for kafka.consumer") {
    val kafkaConsumer = settings.kafka.consumer

    kafkaConsumer.bootstrapServers shouldBe "http"
    kafkaConsumer.groupId shouldBe "127.0.0.0"
    kafkaConsumer.topics shouldBe List()
  }

}