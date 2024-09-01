package me.y9san9.pipeline.phase

import me.y9san9.pipeline.context.PipelineContext

public fun interface PipelinePhase {
    public val name: String? get() = null
    public suspend fun proceed(context: PipelineContext): PipelineContext

    public object Start : PipelinePhase {
        override val name: String = "Start"
        override suspend fun proceed(context: PipelineContext): PipelineContext = context
    }

    public object Finish : PipelinePhase {
        override val name: String = "Finish"
        override suspend fun proceed(context: PipelineContext): PipelineContext = context
    }

    public companion object {
        public fun of(name: String? = null, block: suspend (PipelineContext) -> PipelineContext = { it }): PipelinePhase {
            return object : PipelinePhase {
                override val name = name
                override suspend fun proceed(context: PipelineContext): PipelineContext {
                    return block(context)
                }
            }
        }
    }
}
