package me.y9san9.ksm.restore

import me.y9san9.ksm.logger.log
import me.y9san9.ksm.logger.popLogger
import me.y9san9.ksm.logger.withTag
import me.y9san9.ksm.route.RoutePlugin
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline._PipelineRunnable

public object RestorePipelinePhase : _PipelineRunnable {
    override val name: String = "RestorePipeline"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val pipeline = context.require(RestorePlugin.RestorePipeline)
        val tagged = context.withTag("Restore")
        tagged.log(pipeline.prettyString())
        val result = pipeline.proceed(tagged).popLogger()
        if (result[RestorePlugin.Data] == null) {
            return result.with(RoutePlugin.InitialRoute)
        }
        return result
    }
}
