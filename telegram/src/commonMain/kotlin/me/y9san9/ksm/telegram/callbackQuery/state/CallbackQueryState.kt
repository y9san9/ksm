package me.y9san9.ksm.telegram.callbackQuery.state

import me.y9san9.ksm.telegram.privateMessage.routing.PrivateMessageRouting
import me.y9san9.ksm.telegram.state.StateName
import me.y9san9.ksm.telegram.state.UpdateState
import me.y9san9.ksm.telegram.state.base.UpdateStateBase
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.set

public object CallbackQueryState {
    public class Builder : UpdateState.Builder()
}

@PipelineDsl
public fun PrivateMessageRouting.state(route: StateName, block: CallbackQueryState.Builder.() -> Unit) {
    val builder = CallbackQueryState.Builder()
    builder.context[UpdateStateBase.Config.Route] = route
    builder.block()
    states += builder.build()
}
