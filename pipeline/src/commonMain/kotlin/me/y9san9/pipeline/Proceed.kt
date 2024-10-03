package me.y9san9.pipeline

import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.phase.proceed
import me.y9san9.pipeline.subject.subject

public suspend inline fun Pipeline.proceed(
    parent: PipelineContext,
    block: MutablePipelineContext.() -> Unit
): PipelineContext {
    return proceed(parent, buildPipelineContext(block = block))
}

public suspend fun Pipeline.proceed(
    parent: PipelineContext,
    subject: PipelineContext
): PipelineContext {
    var acc = this.context.build {
        this.context += parent
        this.subject = subject
    }

    val phases = this.context.require(PipelinePhaseList)

    for (phase in phases) {
        if (PipelineSignal.Return in acc) {
            return acc.build { remove(PipelineSignal.Return) }
        }
        if (PipelineSignal.Throw in acc.subject) {
            return acc
        }
        acc = phase.proceed(acc)
    }

    return acc
}
