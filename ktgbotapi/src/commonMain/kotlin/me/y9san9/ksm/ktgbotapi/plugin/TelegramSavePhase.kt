package me.y9san9.ksm.ktgbotapi.plugin

import dev.inmo.tgbotapi.types.toChatId
import me.y9san9.ksm.events.EventsPlugin
import me.y9san9.ksm.restore.RestorePlugin
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline._PipelineRunnable

public object TelegramSavePhase : _PipelineRunnable {
    override val name: String = "TelegramSave"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val storage = context.require(TelegramPlugin.Storage)
        val key = context.require(EventsPlugin.Key) as Long
        val string = context.require(RestorePlugin.String)
        storage.save(key.toChatId(), string)
        return context
    }
}
