package me.y9san9.pipeline

import me.y9san9.pipeline.base.PipelineBase.Config
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.phase.proceed

public suspend fun Pipeline.proceedIn(context: MutablePipelineContext) {
    context.takeFrom(proceed(context.toPipelineContext()))
}

public suspend inline fun Pipeline.proceed(
    block: MutablePipelineContext.() -> Unit
): PipelineContext {
    return proceed(buildPipelineContext(block = block))
}

public suspend fun Pipeline.proceed(
    subject: PipelineContext = PipelineContext.Empty
): PipelineContext {
    var acc = this.context.subject + context.subject + subject
    val phases = this.context.require(Config.PhaseList)

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
