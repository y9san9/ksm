package me.y9san9.ksm.ktgbotapi.plugin

import me.y9san9.ksm.events.EventsPlugin
import me.y9san9.ksm.json.JsonPlugin
import me.y9san9.ksm.state.StatePlugin
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.pipeline
import me.y9san9.pipeline._PipelineRunnable

public object TelegramSetupPhase : _PipelineRunnable {
    override val name: String = "TelegramSetup"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        // cherry-picking some values to goto pipeline
        return context.pipeline(StatePlugin.GotoPipeline) {
            with(EventsPlugin.Event, context.require(EventsPlugin.Event))
            with(EventsPlugin.Key, context.require(EventsPlugin.Key))
            with(JsonPlugin.Json, context.require(JsonPlugin.Json))
            with(TelegramPlugin.Storage, context.require(TelegramPlugin.Storage))
        }
    }
}
