package me.y9san9.ksm.telegram.state.routing

import me.y9san9.pipeline.context.PipelineContext

public interface GotoContinuation {
    public suspend fun resumeWith(subject: PipelineContext): Nothing
}
