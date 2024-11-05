package me.y9san9.pipeline.phase

import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.phase.base.PipelinePhaseBase

public fun interface PipelinePhaseRunnable {
    public suspend fun proceed(context: PipelineContext): PipelineContext
}

public fun PipelinePhase.Builder.runnable(block: suspend MutablePipelineContext.() -> Unit) {
    context[PipelinePhaseBase.Runnable] = object : PipelinePhaseRunnable {
        override suspend fun proceed(context: PipelineContext): PipelineContext {
            return context.build { block() }
        }
        override fun toString() = "PipelinePhaseRunnable { /* code */ }"
    }
}
