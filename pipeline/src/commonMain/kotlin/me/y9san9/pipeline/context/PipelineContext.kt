package me.y9san9.pipeline.context

public interface PipelineContext {
    public val context: PipelineContext get() = this
    public operator fun contains(element: PipelineElement<*>): Boolean
    public operator fun <T> get(element: PipelineElement<T>): T?

    public companion object Empty : PipelineContext {
        override fun <T> get(element: PipelineElement<T>): T? = null
        override fun contains(element: PipelineElement<*>): Boolean = false
    }
}
