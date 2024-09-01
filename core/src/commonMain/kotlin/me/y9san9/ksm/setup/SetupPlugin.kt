package me.y9san9.ksm.setup

import me.y9san9.pipeline.builder.PipelineBuilder
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.withPipeline
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.plugin.PipelinePlugin

public object SetupPlugin : PipelinePlugin {
    override fun apply(builder: PipelineBuilder) {
        with(builder) {
            insertPhaseAfter(PipelinePhase.Start.name, SetupPipelinePhase)
            context = context.withPipeline(Pipeline)
        }
    }

    public object Pipeline : PipelineElement<me.y9san9.pipeline.Pipeline>
}
