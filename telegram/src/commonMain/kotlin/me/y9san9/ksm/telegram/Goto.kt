package me.y9san9.ksm.telegram

import me.y9san9.ksm.router.StateDescriptor
import me.y9san9.ksm.state.StateHandler
import me.y9san9.ksm.state.data.StateData
import me.y9san9.ksm.state.finish
import me.y9san9.ksm.telegram.handler.plugin.TelegramHandlerBase
import me.y9san9.pipeline.context.buildPipelineContext
import me.y9san9.pipeline.context.set

public suspend fun StateHandler.Scope.goto(
    id: String,
    parameters: StateData.Map = StateData.Map(emptyMap()),
    data: StateData = StateData.Null
): Nothing {
    goto(StateDescriptor(id, parameters, data))
}

public suspend fun StateHandler.Scope.goto(descriptor: StateDescriptor): Nothing {
    val subject = buildPipelineContext {
        this[TelegramHandlerBase.Subject.Descriptor] = descriptor
    }
    finish(subject)
}
