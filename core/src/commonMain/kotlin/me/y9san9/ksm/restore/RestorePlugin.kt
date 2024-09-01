package me.y9san9.ksm.restore

import me.y9san9.ksm.route.RoutePipelinePhase
import me.y9san9.ksm.route.RoutePlugin
import me.y9san9.ksm.state.StateData
import me.y9san9.pipeline.builder.PipelineBuilder
import me.y9san9.pipeline.builder.withPipeline
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.dependsOn
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.plugin.PipelinePlugin

public object RestorePlugin : PipelinePlugin {
    override fun apply(builder: PipelineBuilder) {
        with(builder) {
            context.dependsOn(RoutePlugin)

            insertPhaseBefore(RoutePipelinePhase.name, RestorePipelinePhase)

            withPipeline(RestorePipeline)
            withPipeline(SavePipeline)
        }
    }

    public object RestorePipeline : PipelineElement<me.y9san9.pipeline.Pipeline>
    public object SavePipeline : PipelineElement<me.y9san9.pipeline.Pipeline>
    public object Data : PipelineElement<StateData.Map>
    public object String : PipelineElement<kotlin.String>
}
