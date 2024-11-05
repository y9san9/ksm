package me.y9san9.pipeline.context

import me.y9san9.pipeline.plugin.PipelinePlugin

public interface PipelineContext {
    public val context: PipelineContext get() = this
    public operator fun contains(element: PipelineElement<*>): Boolean
    public operator fun <T : Any> get(element: PipelineElement<T>): T?

    public data object Empty : PipelineContext {
        override fun <T : Any> get(element: PipelineElement<T>): T? = null
        override fun contains(element: PipelineElement<*>): Boolean = false
    }
}

public fun <T : Any> PipelineContext.require(
    element: PipelineElement<T>,
    plugin: PipelinePlugin
): T {
    return require(element) { "Plugin '${plugin.name}' is required to be installed. $context" }
}

public inline fun <T : Any> PipelineContext.require(
    element: PipelineElement<T>,
    message: () -> String = { "Element '${element.name}' is required. Context: $this" }
): T {
    if (element !in this) error(message())
    return get(element) as T
}
