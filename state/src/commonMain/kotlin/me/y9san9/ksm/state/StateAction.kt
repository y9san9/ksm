package me.y9san9.ksm.state

import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set

public fun interface StateAction {
    public suspend fun run(scope: StateScope)

    public companion object : PipelineElement<StateAction>
}

public fun StateBuilder.action(block: suspend StateScope.() -> Unit) {
    context[StateAction] = StateAction(block)
}
