package me.y9san9.ksm.telegram.state

import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.pipeline.context.require

public val StateHandler.Scope.data: StateData get() {
    return context.require(Subject.StateData)
}
