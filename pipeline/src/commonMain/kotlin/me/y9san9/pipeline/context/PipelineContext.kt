package me.y9san9.pipeline.context

import me.y9san9.pipeline.plugin.PipelinePlugin

public interface PipelineContext {
    public val context: PipelineContext get() = this
    public operator fun contains(element: PipelineElement<*>): Boolean
    public operator fun <T> get(element: PipelineElement<T>): T?

    public companion object Empty : PipelineContext {
        override fun <T> get(element: PipelineElement<T>): T? = null
        override fun contains(element: PipelineElement<*>): Boolean = false
    }
}

public inline fun <T> PipelineContext.require(
    element: PipelineElement<T>,
    plugin: PipelinePlugin
): T {
    return require(element) { "Plugin '${plugin.name}' is required to be installed. $context" }
}

public inline fun <T> PipelineContext.require(
    element: PipelineElement<T>,
    message: () -> String = { "Element ${element.elementName()} is required. Context: $this" }
): T {
    if (element !in this) error(message())
    @Suppress("UNCHECKED_CAST")
    return get(element) as T
}
