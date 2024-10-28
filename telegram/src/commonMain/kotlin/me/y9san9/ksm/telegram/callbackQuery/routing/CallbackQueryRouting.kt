package me.y9san9.ksm.telegram.callbackQuery.routing

import me.y9san9.ksm.telegram.callbackQuery.group.CallbackQueryGroup
import me.y9san9.ksm.telegram.group.base.UpdateGroupBase
import me.y9san9.ksm.telegram.routing.UpdateRouting
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.set

@PipelineDsl
public class CallbackQueryRouting : UpdateRouting()

@PipelineDsl
public fun CallbackQueryGroup.Builder.routing(block: CallbackQueryRouting.() -> Unit) {
    val routing = CallbackQueryRouting()
    routing.block()
    context[UpdateGroupBase.Config.StateList] = routing.toStateList()
}
