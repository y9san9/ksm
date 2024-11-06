package me.y9san9.ksm.telegram.plugin.group

import me.y9san9.pipeline.context.PipelineContext

public data class StateGroupContext(
    override val context: PipelineContext
) : PipelineContext by context
