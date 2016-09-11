package com.ted.akka.extensions

import akka.actor.{Actor, ExtendedActorSystem, Extension, ExtensionId, ExtensionIdProvider}
import com.typesafe.config.Config


class SettingsExtensionImpl(config: Config) extends Extension {
}

object SettingsExtension extends ExtensionId[SettingsExtensionImpl] with ExtensionIdProvider {
  override def createExtension(system: ExtendedActorSystem) = new SettingsExtensionImpl(system.settings.config)

  override def lookup() = SettingsExtension
}

trait SettingsActor {
  _: Actor =>
  val settings = SettingsExtension(context.system)
}