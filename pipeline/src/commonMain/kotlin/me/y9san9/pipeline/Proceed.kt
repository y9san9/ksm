package me.y9san9.pipeline

import me.y9san9.pipeline.base.PipelineBase
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.phase.proceed

public suspend inline fun Pipeline.proceed(
    context: PipelineContext,
    block: MutablePipelineContext.() -> Unit
): PipelineContext {
    return proceed(context, buildPipelineContext(block = block))
}

public suspend fun Pipeline.proceed(
    context: PipelineContext,
    subject: PipelineContext = PipelineContext.Empty
): PipelineContext {
    var acc = this.context.subject + context.subject + subject
    val phases = this.context.require(PipelineBase.Config.PhaseList)

    for (phase in phases) {
        if (PipelineSignal.Return in acc) {
            return acc.build { remove(PipelineSignal.Return) }
        }
        if (PipelineSignal.Throw in acc) {
            return acc
        }
        acc = phase.proceed(acc)
    }

    return acc
}
