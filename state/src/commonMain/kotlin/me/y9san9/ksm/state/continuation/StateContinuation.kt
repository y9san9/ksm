package me.y9san9.ksm.state.continuation

import me.y9san9.pipeline.context.PipelineContext

public fun interface StateContinuation {
    public suspend fun finish(subject: PipelineContext): Nothing
}
