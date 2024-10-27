package me.y9san9.ksm.telegram.state.routing

import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.ksm.telegram.state.UpdateHandler
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.require

@PipelineDsl
public suspend fun UpdateHandler.Scope.stay(
    data: StateData,
    transition: Boolean = false
): Nothing {
    val descriptor = context.require(Subject.Descriptor)
    goto(StateDescriptor(descriptor.name, descriptor.parameters, data), transition)
}

@PipelineDsl
public suspend fun UpdateHandler.Scope.stay(transition: Boolean = false): Nothing {
    val descriptor = context.require(Subject.Descriptor)
    goto(descriptor, transition)
}
