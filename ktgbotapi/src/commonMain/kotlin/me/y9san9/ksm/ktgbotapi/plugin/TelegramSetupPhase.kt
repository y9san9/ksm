package me.y9san9.ksm.ktgbotapi.plugin

import me.y9san9.ksm.events.EventsPlugin
import me.y9san9.ksm.json.JsonPlugin
import me.y9san9.ksm.state.StatePlugin
import me.y9san9.pipeline.builder.with
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.withPipeline
import me.y9san9.pipeline.phase.PipelinePhase

public object TelegramSetupPhase : PipelinePhase {
    override val name: String = "TelegramSetup"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        // cherry-picking some values to goto pipeline
        return context.withPipeline(StatePlugin.GotoPipeline) {
            with(EventsPlugin.Event, context.require(EventsPlugin.Event))
            with(EventsPlugin.Key, context.require(EventsPlugin.Key))
            with(JsonPlugin.Json, context.require(JsonPlugin.Json))
            with(TelegramPlugin.Storage, context.require(TelegramPlugin.Storage))
        }
    }
}
