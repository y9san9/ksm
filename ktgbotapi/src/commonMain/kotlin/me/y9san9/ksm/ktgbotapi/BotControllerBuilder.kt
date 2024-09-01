package me.y9san9.ksm.ktgbotapi

import me.y9san9.aqueue.AQueue
import me.y9san9.ksm.events.EventsPlugin
import me.y9san9.ksm.ktgbotapi.plugin.TelegramPlugin
import me.y9san9.ksm.ktgbotapi.storage.TelegramStorage
import me.y9san9.pipeline.builder.PipelineBuilder
import me.y9san9.pipeline.builder.buildPipeline
import me.y9san9.pipeline.builder.with
import me.y9san9.pipeline.builder.withPipeline
import me.y9san9.pipeline.context.require

public class BotControllerBuilder(
    public val builder: PipelineBuilder
)

public inline fun buildBotController(
    block: BotControllerBuilder.() -> Unit = {}
): BotController {
    val pipeline = buildPipeline {
        install(TelegramPlugin)
        with(EventsPlugin.Queue, AQueue())

        withPipeline(EventsPlugin.Pipeline) {
            with(TelegramPlugin.Storage, TelegramStorage.Memory())
        }

        val builder = BotControllerBuilder(builder = this)
        block(builder)
    }
    return BotController(pipeline)
}

public var BotControllerBuilder.queue: AQueue
    get() = builder.context.require(EventsPlugin.Queue)
    set(value) = builder.with(EventsPlugin.Queue, value)

public var BotControllerBuilder.storage: TelegramStorage
    get() = builder.context.require(TelegramPlugin.Storage)
    set(value) = builder.with(TelegramPlugin.Storage, value)
