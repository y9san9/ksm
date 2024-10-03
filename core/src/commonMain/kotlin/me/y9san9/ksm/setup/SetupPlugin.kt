package me.y9san9.ksm.setup

import me.y9san9.pipeline.PipelineBuilder
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.pipeline
import me.y9san9.pipeline._PipelineRunnable

public object SetupPlugin : PipelinePlugin {
    override fun apply(builder: PipelineBuilder) {
        with(builder) {
            insertPhaseAfter(_PipelineRunnable.Start.name, SetupPipelinePhase)
            context.pipeline(Pipeline)
        }
    }

    public object Pipeline : PipelineElement<me.y9san9.pipeline.Pipeline>
}
