package me.y9san9.pipeline

import me.y9san9.pipeline.base.PipelineBase
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase

public val Pipeline.phases: List<PipelinePhase>
    get() = context.require(PipelineBase.Config.PhaseList)

public var Pipeline.Builder.phases: List<PipelinePhase>
    get() = context.require(PipelineBase.Config.PhaseList)
    set(value) { context[PipelineBase.Config.PhaseList] = value }
