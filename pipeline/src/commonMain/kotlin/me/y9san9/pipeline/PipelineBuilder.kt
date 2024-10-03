package me.y9san9.pipeline

import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.*


@PipelineDsl
public interface PipelineBuilder {
    public val context: MutablePipelineContext

    public companion object {
        private val default = buildPipelineContext { this[PipelinePhaseList] = emptyList() }

        public fun of(context: PipelineContext? = null): PipelineBuilder {
            return object : PipelineBuilder {
                override var context: MutablePipelineContext =
                    mutablePipelineContextOf(context = context ?: default)

                override fun toString() =
                    "PipelineBuilder(context=$context)"
            }
        }
    }
}

public fun PipelineBuilder.build(): Pipeline {
    return Pipeline.of(context.toPipelineContext())
}
