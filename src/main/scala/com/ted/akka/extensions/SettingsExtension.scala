package com.ted.akka.extensions

import akka.actor.{Actor, ExtendedActorSystem, Extension, ExtensionId}
import com.typesafe.config.Config
import scala.collection.JavaConverters._

class SettingsExtensionImpl(config: Config) extends Extension {

  object application {
    private val applicationConfig = config.getConfig("application")
    val name = applicationConfig.getString("name")
  }

  object kafka {
    private val kafkaConfig = config.getConfig("kafka")

    object producer {
      private val producerConfig = kafkaConfig.getConfig("producer")
      val bootstrapServers = producerConfig.getString("bootstrap.servers")
      val acks = producerConfig.getString("acks")
      val retries = producerConfig.getInt("retries")
      val batchSize = producerConfig.getInt("batch.size")
      val lingerMs = producerConfig.getInt("linger.ms")
      val bufferMemory = producerConfig.getInt("buffer.memory")
    }

    object consumer {
      private val consumerConfig = kafkaConfig.getConfig("consumer")
      val bootstrapServers = consumerConfig.getString("bootstrap.servers")
      val groupId = consumerConfig.getString("group.id")
      val topics = consumerConfig.getStringList("topics").asScala.toList
    }
  }

}

object SettingsExtension extends ExtensionId[SettingsExtensionImpl] {
  override def createExtension(system: ExtendedActorSystem) = new SettingsExtensionImpl(system.settings.config)
}

trait SettingsActor {
  _: Actor =>
  val settings = SettingsExtension(context.system)
}