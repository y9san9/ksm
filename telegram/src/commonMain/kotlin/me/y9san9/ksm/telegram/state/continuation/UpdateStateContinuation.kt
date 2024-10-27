package me.y9san9.ksm.telegram.state.continuation

import me.y9san9.pipeline.context.PipelineContext

public fun interface UpdateStateContinuation {
    public suspend fun finish(subject: PipelineContext): Nothing
}
