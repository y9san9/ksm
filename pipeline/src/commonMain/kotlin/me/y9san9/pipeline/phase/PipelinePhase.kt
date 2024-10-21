package me.y9san9.pipeline.phase

import me.y9san9.pipeline.context.*

// Add before, after and goto,
// goto from A to C will execute all states after A and all states before C
// that way even goto may be intercepted
public data class PipelinePhase(public val context: PipelineContext) {
    public class Builder(context: PipelineContext) {
        public val context: MutablePipelineContext = mutablePipelineContextOf(context)

        public constructor() : this(PipelineContext.Empty)

        public fun build(): PipelinePhase {
            return PipelinePhase(context.toPipelineContext())
        }
    }
}

public inline fun buildPipelinePhase(
    from: PipelinePhase? = null,
    block: PipelinePhase.Builder.() -> Unit
): PipelinePhase {
    val builder = when (from) {
        null -> PipelinePhase.Builder()
        else -> PipelinePhase.Builder(from.context)
    }
    builder.apply(block)
    return builder.build()
}

public inline fun PipelinePhase?.build(
    block: PipelinePhase.Builder.() -> Unit
): PipelinePhase {
    return buildPipelinePhase(from = this, block = block)
}
