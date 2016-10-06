package com.ted.kafka.actors

import akka.actor.Actor
import cakesolutions.kafka.KafkaProducer.Conf
import cakesolutions.kafka.akka.KafkaProducerActor
import com.ted.akka.extensions.SettingsActor
import org.apache.kafka.common.serialization.StringSerializer

class ProducerActor extends Actor with SettingsActor {

  import settings.kafka.producer._

  val kafkaProducerActor = context.actorOf(
    KafkaProducerActor.props(
      Conf(
        keySerializer = new StringSerializer,
        valueSerializer = new StringSerializer,
        bootstrapServers = bootstrapServers,
        acks = acks,
        retries = retries,
        batchSize = batchSize,
        lingerMs = lingerMs,
        bufferMemory = bufferMemory
      )))

  override def receive: Receive = Actor.emptyBehavior
}
