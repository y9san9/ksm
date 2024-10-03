package me.y9san9.ksm.ktgbotapi.start

import me.y9san9.ksm.route.RoutePlugin
import me.y9san9.ksm.setup.SetupPipelinePhase
import me.y9san9.ksm.setup.SetupPlugin
import me.y9san9.pipeline.PipelineBuilder
import me.y9san9.pipeline.builder.pipeline
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.plugin.dependsOn
import me.y9san9.pipeline._PipelineRunnable

public object StartCommandPlugin : PipelinePlugin {
    override fun apply(builder: PipelineBuilder) {
        with(builder) {
            context.dependsOn(SetupPlugin)
            context.dependsOn(RoutePlugin)

            insertPhaseAfter(SetupPipelinePhase.name, StartCommandPipelinePhase)
            insertPhaseBefore(RoutePipelinePhase.name, StartCommandInitialPhase)

            pipeline(Pipeline) {
                insertPhaseAfter(_PipelineRunnable.Start.name, StartCommandPhase)
            }
        }
    }

    public object Pipeline : PipelineElement<me.y9san9.pipeline.Pipeline>
}
