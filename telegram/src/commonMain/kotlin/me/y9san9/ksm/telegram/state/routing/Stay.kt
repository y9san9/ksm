package me.y9san9.ksm.telegram.state.routing

import me.y9san9.ksm.telegram.TelegramFSM.Plugin.StateDescriptor
import me.y9san9.ksm.telegram.routing.StateDescriptor
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.require

@PipelineDsl
public suspend fun StateRouter.stay(
    data: StateData,
    transition: Boolean = false
): Nothing {
    val descriptor = context.require(StateDescriptor)
    goto(StateDescriptor(descriptor.name, descriptor.parameters, data), transition)
}

@PipelineDsl
public suspend fun StateRouter.stay(transition: Boolean = false): Nothing {
    val descriptor = context.require(StateDescriptor)
    goto(descriptor, transition)
}
