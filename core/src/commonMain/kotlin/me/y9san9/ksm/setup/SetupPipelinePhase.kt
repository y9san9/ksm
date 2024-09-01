package me.y9san9.ksm.setup

import me.y9san9.ksm.logger.log
import me.y9san9.ksm.logger.popLogger
import me.y9san9.ksm.logger.withTag
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase

public object SetupPipelinePhase : PipelinePhase {
    override val name: String = "SetupPipeline"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val pipeline = context.require(SetupPlugin.Pipeline)
        val tagged = context.withTag("Setup")
        tagged.log(pipeline.prettyString())
        return pipeline.proceed(tagged).popLogger()
    }
}
