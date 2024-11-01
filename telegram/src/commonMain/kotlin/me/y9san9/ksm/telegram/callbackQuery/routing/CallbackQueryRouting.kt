package me.y9san9.ksm.telegram.callbackQuery.routing

import me.y9san9.ksm.telegram.callbackQuery.state.CallbackQueryState
import me.y9san9.ksm.telegram.state.routing.UpdateRouting
import me.y9san9.ksm.telegram.state.StateName
import me.y9san9.pipeline.annotation.PipelineDsl

@PipelineDsl
public class CallbackQueryRouting {
    public val update: UpdateRouting = UpdateRouting()

    @PipelineDsl
    public var initial: StateName?
        get() = update.initial
        set(value) { update.initial = value }

    @PipelineDsl
    public fun state(name: StateName, block: CallbackQueryState.Builder.() -> Unit) {
        val builder = CallbackQueryState.Builder()
        builder.name = name
        builder.block()
        update.states += builder.update.build()
    }
}
