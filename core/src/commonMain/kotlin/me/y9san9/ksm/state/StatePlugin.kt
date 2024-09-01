package me.y9san9.ksm.state

import kotlinx.coroutines.awaitCancellation
import me.y9san9.ksm.restore.RestorePlugin
import me.y9san9.ksm.route.RoutePlugin
import me.y9san9.ksm.run.RunPlugin
import me.y9san9.ksm.setup.SetupPlugin
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.builder.PipelineBuilder
import me.y9san9.pipeline.builder.withPipeline
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.plugin.PipelinePlugin

public object StatePlugin : PipelinePlugin {
    override fun apply(builder: PipelineBuilder) {
        with(builder) {
            install(RunPlugin)
            install(RoutePlugin)
            install(SetupPlugin)
            install(RestorePlugin)

            withPipeline(RestorePlugin.RestorePipeline) {
                insertPhaseAfter(PipelinePhase.Start.name, StateRestorePhase)
                insertPhaseAfter(StateRestorePhase.name, StateDataSetupPhase)
            }
            withPipeline(RestorePlugin.SavePipeline) {
                insertPhaseBefore(PipelinePhase.Finish.name, StateSavePhase)
            }

            withPipeline(RoutePlugin.Pipeline) {
                insertPhaseAfter(PipelinePhase.Start.name, StateRoutePhase)
            }

            withPipeline(SetupPlugin.Pipeline) {
                insertPhaseAfter(PipelinePhase.Start.name, StateSetupPhase)
            }

            withPipeline(GotoPipeline) {
                install(RunPlugin)
                install(RoutePlugin)
                install(SetupPlugin)

                withPipeline(RoutePlugin.Pipeline) {
                    insertPhaseAfter(PipelinePhase.Start.name, StateRoutePhase)
                }
                withPipeline(SetupPlugin.Pipeline) {
                    insertPhaseAfter(PipelinePhase.Start.name, StateDataSetupPhase)
                }
                withPipeline(RunPlugin.Pipeline) {
                    insertPhaseAfter(PipelinePhase.Start.name, StateTransitionPhase)
                    insertPhaseAfter(StateTransitionPhase.name, StateSavePipelinePhase)
                }
            }

            withPipeline(RunPlugin.Pipeline) {
                insertPhaseAfter(PipelinePhase.Start.name, StateRunPhase)
            }
        }
    }

    public object Goto : PipelineElement<me.y9san9.ksm.state.Goto>
    public object GotoPipeline : PipelineElement<Pipeline>
    public object StateRunnable : PipelineElement<me.y9san9.ksm.state.StateRunnable>
    public object StateTransition : PipelineElement<me.y9san9.ksm.state.StateTransition>
    public object StateDescriptor : PipelineElement<me.y9san9.ksm.state.StateDescriptor>
    public object StateName : PipelineElement<me.y9san9.ksm.state.StateName>
    public object StateData : PipelineElement<me.y9san9.ksm.state.StateData>
}
