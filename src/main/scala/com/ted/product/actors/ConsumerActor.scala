package com.ted.product.actors

import akka.actor.Actor
import cakesolutions.kafka.KafkaConsumer
import cakesolutions.kafka.akka.KafkaConsumerActor
import cakesolutions.kafka.akka.KafkaConsumerActor.{Subscribe, Unsubscribe}
import com.ted.akka.extensions.SettingsActor
import org.apache.kafka.common.serialization.StringDeserializer

class ConsumerActor extends Actor with SettingsActor {

  import settings.kafka.consumer._

  val kafkaConsumerActor = context.actorOf(
    KafkaConsumerActor.props(
      consumerConf = KafkaConsumer.Conf(
        keyDeserializer = new StringDeserializer,
        valueDeserializer = new StringDeserializer,
        bootstrapServers = bootstrapServers,
        groupId = groupId
      ),
      actorConf = KafkaConsumerActor.Conf(),
      downstreamActor = self))

  override def preStart() = {
    super.preStart()
    kafkaConsumerActor ! Subscribe.AutoPartition(topics)
  }

  override def postStop = {
    kafkaConsumerActor ! Unsubscribe
    super.postStop()
  }

  override def receive: Receive = Actor.emptyBehavior
}
