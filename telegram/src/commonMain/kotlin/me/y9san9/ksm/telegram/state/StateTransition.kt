package me.y9san9.ksm.telegram.state

import me.y9san9.ksm.telegram.state.base.StateBase
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.set

public fun interface StateTransition {
    public suspend fun run(scope: Scope)

    public class Scope(public val context: PipelineContext)
}

public fun State.Builder.transition(block: suspend StateTransition.Scope.() -> Unit) {
    context[StateBase.Config.Transition] = StateTransition(block)
}
