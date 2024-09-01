package me.y9san9.pipeline.builder

import me.y9san9.pipeline.Pipeline

public inline fun buildPipeline(
    base: Pipeline? = null,
    block: PipelineBuilder.() -> Unit
): Pipeline {
    val builder = if (base == null) {
        PipelineBuilder()
    } else {
        PipelineBuilder(base.phases.toMutableList(), base.context)
    }
    builder.apply(block)
    return builder.build()
}
