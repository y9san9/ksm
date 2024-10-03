package me.y9san9.ksm.route

import me.y9san9.ksm.run.RunPipeline
import me.y9san9.ksm.run.RunPlugin
import me.y9san9.pipeline.PipelineBuilder
import me.y9san9.pipeline.builder.dependsOn
import me.y9san9.pipeline.context.PipelineElement

public object RoutePlugin : PipelinePlugin {
    override fun apply(builder: PipelineBuilder) {
        with(builder) {
            context.dependsOn(RunPlugin)
            insertPhaseBefore(RunPipeline.name, RoutePipeline)
        }
    }

    public object InitialRoute : PipelineElement<InitialRoute>
    public object List : PipelineElement<PipelineRouteList>
}
