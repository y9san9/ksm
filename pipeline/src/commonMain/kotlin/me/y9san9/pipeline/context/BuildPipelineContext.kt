package me.y9san9.pipeline.context

public inline fun buildPipelineContext(
    context: PipelineContext? = null,
    block: MutablePipelineContext.() -> Unit = {}
): PipelineContext {
    val builder = mutablePipelineContextOf(context = context ?: PipelineContext.Empty)
    builder.apply(block)
    return builder.toPipelineContext()
}

public inline fun PipelineContext?.build(block: MutablePipelineContext.() -> Unit): PipelineContext {
    return buildPipelineContext(context = this, block = block)
}
