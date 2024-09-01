package me.y9san9.ksm.events

import me.y9san9.ksm.state.StatePlugin
import me.y9san9.pipeline.builder.with
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.withPipeline
import me.y9san9.pipeline.phase.PipelinePhase

public object EventsSetupPhase : PipelinePhase {
    override val name: String = "EventsGoto"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        return context.withPipeline(StatePlugin.GotoPipeline) {
            with(EventsPlugin.Event, context.require(EventsPlugin.Event))
        }
    }
}
