package me.y9san9.ksm.route

import me.y9san9.pipeline.PipelineSignal
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.plus
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline._PipelineRunnable

public object InitialRoutePhase : _PipelineRunnable {
    override val name: String = "Initial"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val list = context.require(RoutePlugin.List)
        if (RoutePlugin.InitialRoute in context) {
            return (context + list.initial).with(PipelineSignal.Return)
        }
        return context
    }
}
