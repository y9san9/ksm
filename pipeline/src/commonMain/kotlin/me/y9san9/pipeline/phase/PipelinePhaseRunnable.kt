package me.y9san9.pipeline.phase

import me.y9san9.pipeline.context.*

public fun interface PipelinePhaseRunnable {
    public suspend fun proceed(context: PipelineContext): PipelineContext

    public companion object : PipelineElement<PipelinePhaseRunnable>
}

public suspend fun PipelinePhase.proceed(context: PipelineContext): PipelineContext {
    return this.context.require(PipelinePhaseRunnable).proceed(context)
}

public fun PipelinePhaseBuilder.runnable(block: suspend MutablePipelineContext.() -> Unit) {
    context[PipelinePhaseRunnable] = PipelinePhaseRunnable { context ->
        context.build { block() }
    }
}
