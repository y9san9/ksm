package me.y9san9.ksm.telegram.plugin.transition

import me.y9san9.pipeline.context.PipelineContext

public fun interface GotoContinuation {
    public suspend fun resumeWith(subject: PipelineContext): Nothing
}
