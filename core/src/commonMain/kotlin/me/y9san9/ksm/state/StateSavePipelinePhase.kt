package me.y9san9.ksm.state

import me.y9san9.ksm.logger.log
import me.y9san9.ksm.logger.popLogger
import me.y9san9.ksm.logger.withTag
import me.y9san9.ksm.restore.RestorePlugin
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline._PipelineRunnable

public object StateSavePipelinePhase : _PipelineRunnable {
    override val name: String = "StateSavePipeline"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val savePipeline = context.require(RestorePlugin.SavePipeline)
        val tagged = context.withTag("Save")
        tagged.log(savePipeline.prettyString())
        return savePipeline.proceed(tagged).popLogger()
    }
}
