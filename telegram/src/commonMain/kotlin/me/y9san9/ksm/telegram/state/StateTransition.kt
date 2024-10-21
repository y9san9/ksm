package me.y9san9.ksm.telegram.state

import me.y9san9.ksm.telegram.state.base.StateBase
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.set

public fun interface StateTransition {
    public suspend fun run(scope: StateHandler.Scope)
}

@PipelineDsl
public fun State.Builder.transition(block: suspend StateHandler.Scope.() -> Unit) {
    context[StateBase.Config.Transition] = StateTransition(block)
}
