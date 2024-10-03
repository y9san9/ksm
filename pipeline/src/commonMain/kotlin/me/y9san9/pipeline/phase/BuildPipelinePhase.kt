package me.y9san9.pipeline.phase

public inline fun buildPipelinePhase(
    base: PipelinePhase? = null,
    block: PipelinePhaseBuilder.() -> Unit
): PipelinePhase {
    val builder = PipelinePhaseBuilder.of(base?.context)
    builder.apply(block)
    return builder.build()
}

public inline fun PipelinePhase.build(
    block: PipelinePhaseBuilder.() -> Unit
): PipelinePhase {
    return buildPipelinePhase(base = this, block = block)
}
