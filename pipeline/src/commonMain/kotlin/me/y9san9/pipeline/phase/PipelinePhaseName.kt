package me.y9san9.pipeline.phase

import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set

public object PipelinePhaseName : PipelineElement<String>

public val PipelinePhase.name: String
    get() = context.require(PipelinePhaseName)

public var PipelinePhaseBuilder.name: String
    get() = context.require(PipelinePhaseName)
    set(value) { context[PipelinePhaseName] = value }
