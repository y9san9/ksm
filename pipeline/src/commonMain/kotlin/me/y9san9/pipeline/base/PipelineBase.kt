package me.y9san9.pipeline.base

import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.plugin.PipelinePlugin

public object PipelineBase : PipelinePlugin {
    override val name: String = "PipelineBase"

    public val PhaseList: PipelineElement<List<PipelinePhase>> by PipelineElement
    public val Subject: PipelineElement<PipelineContext> by PipelineElement

    override fun apply(context: MutablePipelineContext) {
        context[PhaseList] = emptyList()
        context[Subject] = PipelineContext.Empty
    }
}
