package me.y9san9.ksm.telegram.plugin.state

import me.y9san9.pipeline.context.PipelineContext

public fun interface StateAction {
    public suspend fun invoke(context: PipelineContext)
}
