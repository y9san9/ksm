package me.y9san9.pipeline.phase

import me.y9san9.pipeline.context.*

public fun interface PipelinePhaseRunnable {
    public suspend fun proceed(context: PipelineContext): PipelineContext
}
