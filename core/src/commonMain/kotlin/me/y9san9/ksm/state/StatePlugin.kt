package me.y9san9.ksm.state

import me.y9san9.ksm.restore.RestorePlugin
import me.y9san9.ksm.route.RoutePlugin
import me.y9san9.ksm.run.RunPlugin
import me.y9san9.ksm.setup.SetupPlugin
import me.y9san9.pipeline.PipelineBuilder
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline._PipelineRunnable

public object StatePlugin : PipelinePlugin {
    override fun apply(builder: PipelineBuilder) {
        with(builder) {
            install(RunPlugin)
            install(RoutePlugin)
            install(SetupPlugin)
            install(RestorePlugin)

            context.withPipeline(RestorePlugin.RestorePipeline) {
                insertPhaseAfter(_PipelineRunnable.Start.name, StateRestorePhase)
                insertPhaseAfter(StateRestorePhase.name, StateDataSetupPhase)
            }
            context.withPipeline(RestorePlugin.SavePipeline) {
                insertPhaseBefore(_PipelineRunnable.Finish.name, StateSavePhase)
            }

            context.withPipeline(RoutePlugin.Pipeline) {
                insertPhaseAfter(_PipelineRunnable.Start.name, StateRoutePhase)
            }

            context.withPipeline(SetupPlugin.Pipeline) {
                insertPhaseAfter(_PipelineRunnable.Start.name, StateSetupPhase)
            }

            context.withPipeline(GotoPipeline) {
                install(RunPlugin)
                install(RoutePlugin)
                install(SetupPlugin)

                context.withPipeline(RoutePlugin.Pipeline) {
                    insertPhaseAfter(_PipelineRunnable.Start.name, StateRoutePhase)
                }
                context.withPipeline(SetupPlugin.Pipeline) {
                    insertPhaseAfter(_PipelineRunnable.Start.name, StateDataSetupPhase)
                }
                context.withPipeline(RunPlugin.Pipeline) {
                    insertPhaseAfter(_PipelineRunnable.Start.name, StateTransitionPhase)
                    insertPhaseAfter(StateTransitionPhase.name, StateSavePipelinePhase)
                }
            }

            context.withPipeline(RunPlugin.Pipeline) {
                insertPhaseAfter(_PipelineRunnable.Start.name, StateRunPhase)
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
