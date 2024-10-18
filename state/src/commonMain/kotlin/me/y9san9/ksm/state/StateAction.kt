package me.y9san9.ksm.state

import me.y9san9.ksm.state.plugin.StateBase
import me.y9san9.pipeline.context.set

public fun interface StateAction {
    public suspend fun run(scope: StateScope)
}

public fun State.Builder.action(block: suspend StateScope.() -> Unit) {
    context[StateBase.Config.Action] = StateAction(block)
}
