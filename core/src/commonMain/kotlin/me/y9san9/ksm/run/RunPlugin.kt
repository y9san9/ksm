package me.y9san9.ksm.run

import me.y9san9.pipeline.PipelineBuilder
import me.y9san9.pipeline.insertPhaseFirst
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.plugin.PipelinePlugin

public object RunPlugin : PipelinePlugin {
    public fun apply(builder: PipelineBuilder) {
        with(builder) {
            insertPhaseFirst(RunPipeline)
        }
    }

    public object Pipeline : PipelineElement<me.y9san9.pipeline.Pipeline>
}
