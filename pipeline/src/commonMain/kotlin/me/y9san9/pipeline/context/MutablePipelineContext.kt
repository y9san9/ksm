package me.y9san9.pipeline.context

public fun mutablePipelineContextOf(context: PipelineContext = PipelineContext.Empty): MutablePipelineContext {
    if (context is MutablePipelineContext) {
        error("This is an error to create MutablePipelineContext with another MutablePipelineContext")
    }

    return object : MutablePipelineContext {
        private var immutableContext: PipelineContext = context
            set(value) {
                if (value is MutablePipelineContext) error("MutablePipelineContext.context must be immutable")
                field = value
            }

        override fun takeFrom(context: PipelineContext) {
            immutableContext = context
        }
        override fun toPipelineContext(): PipelineContext {
            return immutableContext
        }

        override fun toString() = "MutablePipelineContext(context=${this.immutableContext})"
    }
}

public interface MutablePipelineContext : PipelineContext {
    override val context: MutablePipelineContext get() = this

    public fun takeFrom(context: PipelineContext)
    public fun toPipelineContext(): PipelineContext

    override fun contains(element: PipelineElement<*>): Boolean = element in toPipelineContext()
    override fun <T : Any> get(element: PipelineElement<T>): T? = toPipelineContext()[element]
}

public operator fun <T : Any> MutablePipelineContext.set(
    element: PipelineElement<T>,
    value: T?
) {
    takeFrom(context = toPipelineContext() + SingleElementPipelineContext(element, value))
}

public fun MutablePipelineContext.remove(element: PipelineElement<*>) {
    set(element, null)
}
