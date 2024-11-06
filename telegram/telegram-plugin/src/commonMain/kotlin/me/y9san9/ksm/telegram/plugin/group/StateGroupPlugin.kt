package me.y9san9.ksm.telegram.plugin.group

import me.y9san9.ksm.telegram.plugin.state.StateContext
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.plugin.PipelinePlugin

public data object StateGroupPlugin : PipelinePlugin {
    override val name: String = "StateGroupPlugin"

    public val StateList: PipelineElement<List<StateContext>> by PipelineElement
    public val InitialState: PipelineElement<StateContext> by PipelineElement
    public val Storage: PipelineElement<TelegramStorage> by PipelineElement

    override fun apply(context: MutablePipelineContext) {}
}
