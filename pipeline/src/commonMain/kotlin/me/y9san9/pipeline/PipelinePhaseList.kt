package me.y9san9.pipeline

import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase

public object PipelinePhaseList : PipelineElement<List<PipelinePhase>>

public val Pipeline.phases: List<PipelinePhase>
    get() = context.require(PipelinePhaseList)

public var PipelineBuilder.phases: List<PipelinePhase>
    get() = context.require(PipelinePhaseList)
    set(value) { context[PipelinePhaseList] = value }
