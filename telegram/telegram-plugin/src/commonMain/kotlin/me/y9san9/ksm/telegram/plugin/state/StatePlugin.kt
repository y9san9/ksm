package me.y9san9.ksm.telegram.plugin.state

import me.y9san9.ksm.telegram.state.StateName
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.plugin.PipelinePlugin

public data object StatePlugin : PipelinePlugin {
    override val name: String = "StatePlugin"

    public val Name: PipelineElement<StateName> by PipelineElement
    public val Action: PipelineElement<StateAction> by PipelineElement

    override fun apply(context: MutablePipelineContext) {}
}
