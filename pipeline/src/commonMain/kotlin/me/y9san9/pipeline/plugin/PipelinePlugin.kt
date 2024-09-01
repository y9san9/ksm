package me.y9san9.pipeline.plugin

import me.y9san9.pipeline.builder.PipelineBuilder
import me.y9san9.pipeline.context.PipelineElement

public interface PipelinePlugin : PipelineElement<PipelinePlugin> {
    public val name: String get() = this::class.simpleName ?: error("Please override name for anonymous object")
    public fun apply(builder: PipelineBuilder)
}
