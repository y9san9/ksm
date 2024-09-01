package me.y9san9.ksm.ktgbotapi.start

import me.y9san9.ksm.events.EventsPlugin
import me.y9san9.ksm.restore.RestorePipelinePhase
import me.y9san9.ksm.route.RoutePipelinePhase
import me.y9san9.ksm.route.RoutePlugin
import me.y9san9.ksm.setup.SetupPipelinePhase
import me.y9san9.ksm.setup.SetupPlugin
import me.y9san9.pipeline.builder.PipelineBuilder
import me.y9san9.pipeline.builder.withPipeline
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.dependsOn
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.plugin.PipelinePlugin

public object StartCommandPlugin : PipelinePlugin {
    override fun apply(builder: PipelineBuilder) {
        with(builder) {
            context.dependsOn(SetupPlugin)
            context.dependsOn(RoutePlugin)

            insertPhaseAfter(SetupPipelinePhase.name, StartCommandPipelinePhase)
            insertPhaseBefore(RoutePipelinePhase.name, StartCommandInitialPhase)

            withPipeline(Pipeline) {
                insertPhaseAfter(PipelinePhase.Start.name, StartCommandPhase)
            }
        }
    }

    public object Pipeline : PipelineElement<me.y9san9.pipeline.Pipeline>
}
