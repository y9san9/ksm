package me.y9san9.pipeline

public inline fun buildPipeline(
    base: Pipeline? = null,
    block: PipelineBuilder.() -> Unit = {}
): Pipeline {
    val builder = PipelineBuilder.of(base?.context)
    builder.apply(block)
    return builder.build()
}

public inline fun Pipeline?.build(
    block: PipelineBuilder.() -> Unit = {}
): Pipeline {
    return buildPipeline(base = this, block)
}
