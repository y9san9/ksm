package me.y9san9.pipeline.phase

import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.base.PipelinePhaseBase.Config


public suspend fun PipelinePhase.proceed(subject: PipelineContext): PipelineContext {
    return context.require(Config.Runnable).proceed(subject)
}
