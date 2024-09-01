package me.y9san9.ksm.run

import me.y9san9.ksm.logger.log
import me.y9san9.ksm.logger.popLogger
import me.y9san9.ksm.logger.withTag
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase

public object RunPipelinePhase : PipelinePhase {
    override val name: String = "RunPipeline"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val pipeline = context.require(RunPlugin.Pipeline)
        val tagged = context.withTag("Run")
        tagged.log(pipeline.prettyString())
        return pipeline.proceed(tagged).popLogger()
    }
}
