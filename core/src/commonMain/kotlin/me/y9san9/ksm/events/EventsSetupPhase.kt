package me.y9san9.ksm.events

import me.y9san9.ksm.state.StatePlugin
import me.y9san9.pipeline.builder.with
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.pipeline
import me.y9san9.pipeline._PipelineRunnable

public object EventsSetupPhase : _PipelineRunnable {
    override val name: String = "EventsGoto"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        return context.pipeline(StatePlugin.GotoPipeline) {
            this.context.with(EventsPlugin.Event, context.require(EventsPlugin.Event))
        }
    }
}
