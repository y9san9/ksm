package me.y9san9.ksm.state

import me.y9san9.pipeline.context.PipelineContext

// todo: This is only relevant for event-based pipelines, refactor it
public interface StateTransition {
    public suspend fun run(context: PipelineContext)
}
