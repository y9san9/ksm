package me.y9san9.ksm.telegram.privateMessage.routing

import me.y9san9.ksm.telegram.privateMessage.state.PrivateMessageState
import me.y9san9.ksm.telegram.state.routing.UpdateRouting
import me.y9san9.ksm.telegram.state.StateName
import me.y9san9.pipeline.annotation.PipelineDsl

@PipelineDsl
public class PrivateMessageRouting {
    public val update: UpdateRouting = UpdateRouting()

    @PipelineDsl
    public var initial: StateName?
        get() = update.initial
        set(value) { update.initial = value }

    @PipelineDsl
    public fun state(name: StateName, block: PrivateMessageState.Builder.() -> Unit) {
        val builder = PrivateMessageState.Builder()
        builder.name = name
        builder.block()
        update.states += builder.update.build()
    }
}
