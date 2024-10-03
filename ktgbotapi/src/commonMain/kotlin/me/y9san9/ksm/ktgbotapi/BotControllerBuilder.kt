package me.y9san9.ksm.ktgbotapi

import me.y9san9.aqueue.AQueue
import me.y9san9.ksm.events.EventsPlugin
import me.y9san9.ksm.ktgbotapi.plugin.TelegramPlugin
import me.y9san9.ksm.ktgbotapi.plugin.installTelegram
import me.y9san9.ksm.ktgbotapi.storage.TelegramStorage
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.buildPipelineContext
import me.y9san9.pipeline.builder.*
import me.y9san9.pipeline.context.require

public class BotControllerBuilder(
    public val builder: MutablePipelineContext
)

public inline fun buildBotController(
    block: BotControllerBuilder.() -> Unit = {}
): BotController {
    val context = buildPipelineContext {
        val builder = BotControllerBuilder(builder = this)
        builder.installTelegram()
        block(builder)
    }
    return BotController(context)
}

public var BotControllerBuilder.queue: AQueue
    get() = builder.value.require(EventsPlugin.Queue)
    set(value) = builder.with(EventsPlugin.Queue, value)

public var BotControllerBuilder.storage: TelegramStorage
    get() = builder.value.require(TelegramPlugin.Storage)
    set(value) = builder.with(TelegramPlugin.Storage, value)
