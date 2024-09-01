package me.y9san9.ksm.ktgbotapi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.y9san9.ksm.events.EventsPlugin
import me.y9san9.ksm.ktgbotapi.event.MessageEvent
import me.y9san9.ksm.logger.log
import me.y9san9.ksm.logger.logger
import me.y9san9.ksm.logger.withLogger
import me.y9san9.ksm.logger.withTag
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.with

public class BotController(private val pipeline: Pipeline) {
    public suspend fun proceed(events: Flow<MessageEvent>) {
        val tagged = pipeline.context.withTag("Main")
        tagged.log(pipeline.prettyString())
        val flow = events.map { event ->
            PipelineContext.with(EventsPlugin.Event, event)
        }
        pipeline.proceed(PipelineContext.with(EventsPlugin.Flow, flow).withLogger(tagged.logger))
    }
}
