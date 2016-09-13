package com.ted.akka.extensions

import akka.actor.{Actor, ExtendedActorSystem, Extension, ExtensionId, ExtensionIdProvider}
import com.typesafe.config.Config


class SettingsExtensionImpl(config: Config) extends Extension {

  object service {
    private val serviceConfig = config.getConfig("service")
    val name = serviceConfig.getString("name")
  }

  object productService {
    private val productServiceConfig = config.getConfig("product-service")
    val protocol = productServiceConfig.getString("protocol")
    val host = productServiceConfig.getString("host")
    val port = productServiceConfig.getString("port")

    object products {
      private val productsConfig = productServiceConfig.getConfig("products")
      val uri = productsConfig.getString("uri")
    }
  }

}

object SettingsExtension extends ExtensionId[SettingsExtensionImpl] with ExtensionIdProvider {
  override def createExtension(system: ExtendedActorSystem) = new SettingsExtensionImpl(system.settings.config)

  override def lookup() = SettingsExtension
}

trait SettingsActor {
  _: Actor =>
  val settings = SettingsExtension(context.system)
}