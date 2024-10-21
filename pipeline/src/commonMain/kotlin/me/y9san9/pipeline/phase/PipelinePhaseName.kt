package me.y9san9.pipeline.phase

import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.base.PipelinePhaseBase.Config

public val PipelinePhase.name: String
    get() = context.require(Config.Name)

public var PipelinePhase.Builder.name: String
    get() = context.require(Config.Name)
    set(value) { context[Config.Name] = value }
