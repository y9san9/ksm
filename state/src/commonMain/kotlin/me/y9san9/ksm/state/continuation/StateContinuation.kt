package me.y9san9.ksm.state.continuation

import me.y9san9.pipeline.context.PipelineElement

public fun interface StateContinuation {
    public suspend fun finish(): Nothing
    public companion object : PipelineElement<StateContinuation>
}
