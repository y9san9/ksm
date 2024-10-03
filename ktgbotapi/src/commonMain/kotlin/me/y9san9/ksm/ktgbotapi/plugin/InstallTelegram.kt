package me.y9san9.ksm.ktgbotapi.plugin

import me.y9san9.aqueue.AQueue
import me.y9san9.ksm.events.EventsPlugin
import me.y9san9.ksm.ktgbotapi.BotControllerBuilder
import me.y9san9.ksm.ktgbotapi.storage.TelegramStorage
import me.y9san9.pipeline.builder.with
import me.y9san9.pipeline.builder.pipeline

public fun BotControllerBuilder.installTelegram() {
    with (builder) {
        pipeline(TelegramPlugin.MainPipeline) {
            install(TelegramPlugin)

            context.with(EventsPlugin.Queue, AQueue())

            context.pipeline(EventsPlugin.Pipeline) {
                context.with(TelegramPlugin.Storage, TelegramStorage.Memory())
            }
        }
    }
}
