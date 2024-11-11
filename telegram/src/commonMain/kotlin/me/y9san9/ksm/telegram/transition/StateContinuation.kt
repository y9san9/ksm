package me.y9san9.ksm.telegram.transition

import me.y9san9.pipeline.context.PipelineContext

public fun interface StateContinuation {
    public suspend fun resumeWith(subject: PipelineContext): Nothing
}
