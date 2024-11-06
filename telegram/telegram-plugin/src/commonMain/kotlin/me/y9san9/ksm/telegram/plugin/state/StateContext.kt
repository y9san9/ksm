package me.y9san9.ksm.telegram.plugin.state

import me.y9san9.pipeline.context.PipelineContext

public data class StateContext(
    override val context: PipelineContext
): PipelineContext by context
