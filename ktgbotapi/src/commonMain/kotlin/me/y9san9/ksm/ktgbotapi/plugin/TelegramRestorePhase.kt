package me.y9san9.ksm.ktgbotapi.plugin

import dev.inmo.tgbotapi.types.toChatId
import me.y9san9.ksm.events.EventsPlugin
import me.y9san9.ksm.restore.RestorePlugin
import me.y9san9.pipeline.PipelineSignal
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline.phase.PipelinePhase

public object TelegramRestorePhase : PipelinePhase {
    override val name: String = "TelegramRestore"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val storage = context.require(TelegramPlugin.Storage)
        val key = context.require(EventsPlugin.Key) as Long
        val descriptor = storage.restore(key.toChatId()) ?: return context.with(PipelineSignal.Return)
        return context.with(RestorePlugin.String, descriptor)
    }
}
