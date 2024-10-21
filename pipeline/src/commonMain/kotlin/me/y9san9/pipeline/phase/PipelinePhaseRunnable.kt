package me.y9san9.pipeline.phase

import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.phase.base.PipelinePhaseBase.Config

public fun interface PipelinePhaseRunnable {
    public suspend fun proceed(context: PipelineContext): PipelineContext
}

public fun PipelinePhase.Builder.runnable(block: suspend MutablePipelineContext.() -> Unit) {
    context[Config.Runnable] = PipelinePhaseRunnable { context ->
        context.build { block() }
    }
}
