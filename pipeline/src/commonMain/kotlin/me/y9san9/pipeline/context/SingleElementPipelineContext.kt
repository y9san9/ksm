package me.y9san9.pipeline.context

public class SingleElementPipelineContext<T>(
    public val element: PipelineElement<T>,
    public val value: T?
) : PipelineContext {
    override fun contains(element: PipelineElement<*>): Boolean {
        return this.element == element
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(element: PipelineElement<T>): T? {
        if (this.element != element) return null
        return value as T
    }
}
