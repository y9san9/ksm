package me.y9san9.pipeline.base

import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.plugin.PipelinePlugin

public object PipelineBase : PipelinePlugin {
    override val name: String = "PipelineBase"

    override fun apply(context: MutablePipelineContext) {
        context[Config.PhaseList] = emptyList()
        context[Config.Subject] = buildPipelineContext()
    }

    public object Config {
        public object PhaseList : PipelineElement<List<PipelinePhase>>
        public object Subject : PipelineElement<PipelineContext>
    }
}
