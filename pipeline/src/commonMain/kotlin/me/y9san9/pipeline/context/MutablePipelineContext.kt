package me.y9san9.pipeline.context

public fun mutablePipelineContextOf(context: PipelineContext): MutablePipelineContext {
    if (context is MutablePipelineContext) {
        error("This is an error to create MutablePipelineContext with another MutablePipelineContext")
    }

    return object : MutablePipelineContext {
        override var context: PipelineContext = context
            set(value) {
                if (value is MutablePipelineContext) error("MutablePipelineContext.context must be immutable")
                field = value
            }

        override fun toString() = "MutablePipelineContext(context=${this.context})"
    }
}

public interface MutablePipelineContext : PipelineContext {
    override var context: PipelineContext

    override fun contains(element: PipelineElement<*>): Boolean = element in context
    override fun <T> get(element: PipelineElement<T>): T? = context[element]
}

public fun MutablePipelineContext.toPipelineContext(): PipelineContext {
    return context.context
}

public operator fun <T> MutablePipelineContext.set(
    element: PipelineElement<T>,
    value: T?
) {
    context += SingleElementPipelineContext(element, value)
}

public fun MutablePipelineContext.remove(element: PipelineElement<*>) {
    set(element, null)
}
