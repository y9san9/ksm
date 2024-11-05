package me.y9san9.ksm.telegram.state.base

import me.y9san9.ksm.telegram.state.StateContinuation
import me.y9san9.ksm.telegram.state.UpdateHandler
import me.y9san9.ksm.telegram.state.StateName
import me.y9san9.ksm.telegram.state.UpdateTransition
import me.y9san9.ksm.telegram.state.routing.GotoCommand
import me.y9san9.ksm.telegram.state.routing.StateDescriptor
import me.y9san9.pipeline.context.MutablePipelineContext
import me.y9san9.pipeline.context.PipelineElement
import me.y9san9.pipeline.plugin.PipelinePlugin

public data object UpdateStateBase : PipelinePlugin {
    override val name: String = "UpdateStateBase"

    // State Config
    public val Name: PipelineElement<StateName> by PipelineElement
    public val Transition: PipelineElement<UpdateTransition> by PipelineElement
    public val Handler: PipelineElement<UpdateHandler> by PipelineElement

    // State Pipeline
    public val Continuation: PipelineElement<StateContinuation> by PipelineElement
    public val GotoCommand: PipelineElement<GotoCommand> by PipelineElement
    public val Descriptor: PipelineElement<StateDescriptor> by PipelineElement

    override fun apply(context: MutablePipelineContext) {}
}
