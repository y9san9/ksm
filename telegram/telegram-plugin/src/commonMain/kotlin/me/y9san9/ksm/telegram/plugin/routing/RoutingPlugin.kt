package me.y9san9.ksm.telegram.plugin.routing

import me.y9san9.ksm.telegram.plugin.state.StateContext
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.insertPhaseLast
import me.y9san9.pipeline.plugin.PipelinePlugin
import me.y9san9.pipeline.set

public data object RoutingPlugin : PipelinePlugin {
    override val name: String = "RoutingPlugin"

    public val Pipeline: PipelineElement<Pipeline> by PipelineElement

    public val SelectedState: PipelineElement<StateContext> by PipelineElement

    override fun apply(context: MutablePipelineContext) {
        context.set(Pipeline) {
            insertPhaseLast(FindByNamePhase)
        }
    }
}
