package me.y9san9.pipeline.plugin

import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement

public interface PipelinePlugin : PipelineElement<Unit> {
    public fun apply(context: MutablePipelineContext)
}
