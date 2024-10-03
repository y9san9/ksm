package me.y9san9.ksm.state

import me.y9san9.ksm.restore.RestorePlugin
import me.y9san9.ksm.route.RoutePlugin
import me.y9san9.pipeline.builder.with
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline.context.pipeline
import me.y9san9.pipeline._PipelineRunnable

public object StateSetupPhase : _PipelineRunnable {
    override val name: String = "StateSetup"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        return context.pipeline(StatePlugin.GotoPipeline) {
            this.context.with(RoutePlugin.List, context.require(RoutePlugin.List))
            this.context.with(RestorePlugin.SavePipeline, context.require(RestorePlugin.SavePipeline))
        }
    }
}
