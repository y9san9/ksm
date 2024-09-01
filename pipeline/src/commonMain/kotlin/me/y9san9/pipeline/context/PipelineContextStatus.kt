package me.y9san9.pipeline.context

import me.y9san9.pipeline.PipelineSignal

public val PipelineContext.isSuccess: Boolean
    get() = !isFailure

public val PipelineContext.isFailure: Boolean
    get() = PipelineSignal.Throw in this
