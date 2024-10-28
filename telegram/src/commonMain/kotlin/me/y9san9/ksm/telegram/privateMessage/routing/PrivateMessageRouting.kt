package me.y9san9.ksm.telegram.privateMessage.routing

import me.y9san9.ksm.telegram.group.base.UpdateGroupBase
import me.y9san9.ksm.telegram.privateMessage.group.PrivateMessageGroup
import me.y9san9.ksm.telegram.routing.UpdateRouting
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.set

@PipelineDsl
public class PrivateMessageRouting : UpdateRouting()

@PipelineDsl
public fun PrivateMessageGroup.Builder.routing(block: PrivateMessageRouting.() -> Unit) {
    val routing = PrivateMessageRouting()
    routing.block()
    context[UpdateGroupBase.Config.StateList] = routing.toStateList()
}
