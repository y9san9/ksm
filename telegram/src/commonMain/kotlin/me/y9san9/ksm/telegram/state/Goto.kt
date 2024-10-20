package me.y9san9.ksm.telegram.state

import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase
import me.y9san9.ksm.telegram.routing.StateDescriptor
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.pipeline.context.buildPipelineContext
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set

public suspend fun StateHandler.Scope.goto(
    id: String,
    data: StateData = StateData.Null
): Nothing {
    goto(StateDescriptor(id, StateData.Map.Empty, data))
}

public suspend fun StateHandler.Scope.goto(descriptor: StateDescriptor): Nothing {
    val subject = buildPipelineContext {
        set(TelegramUpdateHandlerBase.Subject.Descriptor, descriptor)
    }
    context.require(TelegramUpdateHandlerBase.Subject.Continuation).finish(subject)
}
