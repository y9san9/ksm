package me.y9san9.pipeline.builder

import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.builder.buildPipeline
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline.context.withPipeline
import me.y9san9.pipeline.plugin.PipelinePlugin

public fun <T> PipelineBuilder.with(element: PipelineElement<T>, value: T?) {
    context = context.with(element, value)
}

public inline fun PipelineBuilder.withPipeline(
    element: PipelineElement<Pipeline>,
    block: PipelineBuilder.() -> Unit = { }
) {
    context = context.withPipeline(element, block)
}

public fun <T : PipelineElement<T>> PipelineBuilder.with(element: T) {
    return with(element, element)
}
