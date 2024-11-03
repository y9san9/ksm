package me.y9san9.ksm.telegram.state.base

import me.y9san9.ksm.telegram.state.UpdateHandler
import me.y9san9.ksm.telegram.state.StateName
import me.y9san9.ksm.telegram.state.UpdateTransition
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.plugin.PipelinePlugin

public object UpdateStateBase : PipelinePlugin {
    override val name: String = "UpdateStateBase"

    public val Name: PipelineElement<StateName> by PipelineElement
    public val Transition: PipelineElement<UpdateTransition> by PipelineElement
    public val Handler: PipelineElement<UpdateHandler> by PipelineElement

    override fun apply(context: MutablePipelineContext) {}
}
