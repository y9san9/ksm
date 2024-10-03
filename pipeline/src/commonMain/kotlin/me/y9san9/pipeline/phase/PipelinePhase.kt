package me.y9san9.pipeline.phase

import me.y9san9.pipeline.context.*

public interface PipelinePhase {
    public val context: PipelineContext

    public companion object {
        public fun of(context: PipelineContext): PipelinePhase {
            return object : PipelinePhase {
                override val context = context
                override fun toString() = "PipelinePhase(context=$context)"
            }
        }
    }
}
