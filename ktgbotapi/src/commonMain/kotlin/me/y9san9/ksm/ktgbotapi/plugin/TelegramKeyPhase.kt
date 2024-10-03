package me.y9san9.ksm.ktgbotapi.plugin

import me.y9san9.ksm.events.EventsPlugin
import me.y9san9.ksm.ktgbotapi.event.MessageEvent
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline._PipelineRunnable

public object TelegramKeyPhase : _PipelineRunnable {
    override val name: String = "TelegramKey"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val event = context.require(EventsPlugin.Event) as MessageEvent
        val key = event.message.user.id.chatId.long
        return context.with(EventsPlugin.Key, key)
    }
}
