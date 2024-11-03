package me.y9san9.pipeline.phase

import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.base.PipelinePhaseBase

public val PipelinePhase.name: String
    get() = context.require(PipelinePhaseBase.Name)

public var PipelinePhase.Builder.name: String
    get() = context.require(PipelinePhaseBase.Name)
    set(value) { context[PipelinePhaseBase.Name] = value }
