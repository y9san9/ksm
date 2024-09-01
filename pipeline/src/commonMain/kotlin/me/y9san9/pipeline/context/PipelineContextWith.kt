package me.y9san9.pipeline.context

import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.builder.PipelineBuilder
import me.y9san9.pipeline.builder.buildPipeline
import me.y9san9.pipeline.plugin.PipelinePlugin

public fun <T> PipelineContext.require(element: PipelineElement<T>): T {
    return get(element) ?: error("$element is required")
}
public fun PipelineContext.dependsOn(plugin: PipelinePlugin) {
    get(plugin) ?: error("Plugin '${plugin.name}' is required to be installed")
}

public inline fun PipelineContext.withPipeline(
    element: PipelineElement<Pipeline>,
    block: PipelineBuilder.() -> Unit = { }
): PipelineContext {
    return withDecorator(element) { pipeline ->
        buildPipeline(pipeline, block)
    }
}

public inline fun <T> PipelineContext.requireDecorator(
    element: PipelineElement<T>,
    decorator: (T) -> T
): PipelineContext {
    return with(element, decorator(this.require(element)))
}

public inline fun <T> PipelineContext.withDecorator(
    element: PipelineElement<T>,
    decorator: (T?) -> T?
): PipelineContext {
    return with(element, decorator(this[element]))
}

public fun <T : PipelineElement<T>> PipelineContext.with(element: T): PipelineContext {
    return with(element, element)
}

public fun PipelineContext.remove(element: PipelineElement<*>): PipelineContext {
    return with(element, value = null)
}

public fun PipelineContext.cherryPick(element: PipelineElement<*>, from: PipelineContext): PipelineContext {
    fun <T> PipelineContext.unchecked(element: PipelineElement<T>, value: Any?): PipelineContext {
        @Suppress("UNCHECKED_CAST")
        return with(element, value as T)
    }
    return unchecked(element, from[element])
}

public fun <T> PipelineContext.with(
    element: PipelineElement<T>,
    value: T?
): PipelineContext {
    val context = SingleElementPipelineContext(element, value)
    return this + context
}
