package me.y9san9.ksm.ktgbotapi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.y9san9.ksm.events.EventsPlugin
import me.y9san9.ksm.ktgbotapi.event.MessageEvent
import me.y9san9.ksm.ktgbotapi.plugin.TelegramPlugin
import me.y9san9.ksm.logger.withLogger
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.with

public suspend fun BotController.proceed(events: Flow<MessageEvent>) {
    val pipeline = context.require(TelegramPlugin.MainPipeline)

    val tagged = pipeline.context.withTag("Main")
    tagged.log(pipeline.prettyString())
    val flow = events.map { event ->
        PipelineContext.with(EventsPlugin.Event, event)
    }
    pipeline.proceed(PipelineContext.with(EventsPlugin.Flow, flow).withLogger(tagged.logger))
}
