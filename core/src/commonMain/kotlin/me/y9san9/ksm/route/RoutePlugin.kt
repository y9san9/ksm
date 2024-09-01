package me.y9san9.ksm.route

import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.ksm.run.RunPipelinePhase
import me.y9san9.ksm.run.RunPlugin
import me.y9san9.pipeline.builder.PipelineBuilder
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.dependsOn
import me.y9san9.pipeline.context.withPipeline

public object RoutePlugin : PipelinePlugin {
    override fun apply(builder: PipelineBuilder) {
        with(builder) {
            context.dependsOn(RunPlugin)

            insertPhaseBefore(RunPipelinePhase.name, RoutePipelinePhase)
            context = context.withPipeline(Pipeline)
        }
    }

    public object InitialRoute : PipelineElement<InitialRoute>
    public object List : PipelineElement<PipelineRouteList>
    public object Pipeline : PipelineElement<me.y9san9.pipeline.Pipeline>
}
