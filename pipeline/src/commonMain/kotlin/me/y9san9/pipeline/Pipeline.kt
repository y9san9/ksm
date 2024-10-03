package me.y9san9.pipeline

import me.y9san9.pipeline.context.*

public interface Pipeline {
    public val context: PipelineContext

    public companion object {
        public fun require(context: PipelineContext) {
            context.require(PipelinePhaseList)
        }

        public fun of(context: PipelineContext): Pipeline {
            require(context)
            return object : Pipeline {
                override val context = context
                override fun toString(): String = "Pipeline(context=$context)"
            }
        }
    }
}
