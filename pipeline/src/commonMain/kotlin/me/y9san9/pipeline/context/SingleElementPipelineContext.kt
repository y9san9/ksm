package me.y9san9.pipeline.context

public fun <T : Any> pipelineContextOf(element: PipelineElement<T>, value: T?): PipelineContext {
    return SingleElementPipelineContext(element, value)
}

public class SingleElementPipelineContext<T : Any>(
    public val element: PipelineElement<T>,
    public val value: T?
) : PipelineContext {
    override fun contains(element: PipelineElement<*>): Boolean {
        return this.element == element
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> get(element: PipelineElement<T>): T? {
        if (this.element != element) return null
        return value as T
    }
}
