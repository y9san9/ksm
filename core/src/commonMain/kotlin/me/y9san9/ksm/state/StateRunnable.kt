package me.y9san9.ksm.state

import me.y9san9.pipeline.context.PipelineContext

public interface StateRunnable {
    public suspend fun run(context: PipelineContext)
}
