package me.y9san9.ksm.state

import me.y9san9.ksm.state.continuation.StateContinuation
import me.y9san9.pipeline.context.require

public suspend fun StateScope.finish(): Nothing {
    context.require(StateContinuation).finish()
}
