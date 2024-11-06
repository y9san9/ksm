package me.y9san9.ksm.telegram.plugin.transition

import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.plugin.PipelinePlugin

public data object TransitionPlugin : PipelinePlugin {
    override val name: String = "RoutingPlugin"

    public val Pipeline: PipelineElement<Pipeline> by PipelineElement

    override fun apply(context: MutablePipelineContext) {

    }
}
