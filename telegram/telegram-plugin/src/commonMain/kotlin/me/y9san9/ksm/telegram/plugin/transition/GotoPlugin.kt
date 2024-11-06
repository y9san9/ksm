package me.y9san9.ksm.telegram.plugin.transition

import me.y9san9.ksm.telegram.routing.GotoCommand
import me.y9san9.pipeline.Pipeline
import me.y9san9.pipeline.buildPipeline
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.plugin.PipelinePlugin

public data object GotoPlugin : PipelinePlugin {
    override val name: String = "GotoPlugin"

    public val Pipeline: PipelineElement<Pipeline> by PipelineElement
    public val Command: PipelineElement<GotoCommand> by PipelineElement
    public val Continuation: PipelineElement<GotoContinuation> by PipelineElement

    override fun apply(context: MutablePipelineContext) {
        context[Pipeline] = buildPipeline {
            val stateList =
        }
    }
}
