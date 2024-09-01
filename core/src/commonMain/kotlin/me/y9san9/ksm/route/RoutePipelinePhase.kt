package me.y9san9.ksm.route

import me.y9san9.ksm.logger.log
import me.y9san9.ksm.logger.popLogger
import me.y9san9.ksm.logger.withTag
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.plus
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase

public object RoutePipelinePhase : PipelinePhase {
    override val name: String = "RoutePipeline"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val list = context.require(RoutePlugin.List)
        if (RoutePlugin.InitialRoute in context) {
            return context + list.initial
        }
        val pipeline = context.require(RoutePlugin.Pipeline)
        val tagged = context.withTag("Route")
        tagged.log(pipeline.prettyString())
        val result = pipeline.proceed(tagged).popLogger()
        return result
    }
}
