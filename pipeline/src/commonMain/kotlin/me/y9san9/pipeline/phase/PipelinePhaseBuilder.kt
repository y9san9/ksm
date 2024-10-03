package me.y9san9.pipeline.phase

import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.*

@PipelineDsl
public interface PipelinePhaseBuilder {
    public val context: MutablePipelineContext

    public companion object {
        public fun of(context: PipelineContext? = null): PipelinePhaseBuilder {
            return object : PipelinePhaseBuilder {
                override val context = mutablePipelineContextOf(context = context ?: PipelineContext.Empty)
                override fun toString() = "PipelinePhaseBuilder(context=${this.context})"
            }
        }
    }
}

public fun PipelinePhaseBuilder.build(): PipelinePhase {
    return PipelinePhase.of(context.toPipelineContext())
}
