package me.y9san9.ksm.state

import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline.phase.PipelinePhase
import kotlin.coroutines.*

public object StateTransitionPhase : PipelinePhase {
    override val name: String = "StateTransition"

    override suspend fun proceed(context: PipelineContext): PipelineContext {
        val transition = context[StatePlugin.StateTransition] ?: return context
        transition.run(context)
        return context
    }
}
