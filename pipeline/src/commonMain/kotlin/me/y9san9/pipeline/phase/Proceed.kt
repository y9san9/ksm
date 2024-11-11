package me.y9san9.pipeline.phase

import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require

public suspend fun PipelinePhase.proceed(subject: PipelineContext): PipelineContext {
    return context.require(PipelinePhase.Runnable).proceed(subject)
}

public suspend fun PipelinePhase.proceedIn(context: MutablePipelineContext) {
    context.takeFrom(proceed(context.toPipelineContext()))
}
