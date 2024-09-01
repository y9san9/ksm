package me.y9san9.ksm.state

import me.y9san9.ksm.restore.RestorePlugin
import me.y9san9.ksm.route.RoutePlugin
import me.y9san9.pipeline.builder.with
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline.context.withPipeline
import me.y9san9.pipeline.phase.PipelinePhase

public object StateSetupPhase : PipelinePhase {
    override val name: String = "StateSetup"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        return context.withPipeline(StatePlugin.GotoPipeline) {
            with(RoutePlugin.List, context.require(RoutePlugin.List))
            with(RestorePlugin.SavePipeline, context.require(RestorePlugin.SavePipeline))
        }
    }
}
