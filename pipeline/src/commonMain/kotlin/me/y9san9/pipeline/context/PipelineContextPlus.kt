package me.y9san9.pipeline.context

public operator fun MutablePipelineContext.plusAssign(element: PipelineElement<Unit>) {
    this[element] = Unit
}

public operator fun MutablePipelineContext.plusAssign(context: PipelineContext) {
    takeFrom(context = toPipelineContext() + context)
}

public operator fun PipelineContext.plus(context: PipelineContext): PipelineContext {
    if (this is MutablePipelineContext) error("Receiver was MutablePipelineContext")
    if (context is MutablePipelineContext) error("context was MutablePipelineContext")

    val leftMap = this.map
    val rightMap = context.map

    if (leftMap != null && rightMap != null) {
        return MapPipelineContext(map = leftMap + rightMap)
    }

    return CombinedPipelineContext(contexts = this.contexts + context.contexts).apply { checkRecursion() }
}

private fun CombinedPipelineContext.checkRecursion() {
    this.contexts.forEach { context ->
        if (context is CombinedPipelineContext) { context.checkRecursion() }
    }
}

private class CombinedPipelineContext(val contexts: List<PipelineContext>) : PipelineContext {
    override fun contains(element: PipelineElement<*>): Boolean {
        return contexts.any { context -> element in context }
    }
    override fun <T : Any> get(element: PipelineElement<T>): T? {
        for (context in contexts.asReversed()) {
            if (element in context) return context[element]
        }
        return null
    }
}

private val PipelineContext.contexts: List<PipelineContext> get() = when (this) {
    is CombinedPipelineContext -> contexts
    else -> listOf(this)
}

private val PipelineContext.map: Map<PipelineElement<*>, *>? get() = when (this) {
    is MapPipelineContext -> map
    is SingleElementPipelineContext<*> -> mapOf(this.element to this.value)
    is PipelineContext.Empty -> emptyMap<PipelineElement<*>, Any?>()
    else -> null
}
