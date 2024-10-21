package me.y9san9.ksm.telegram.routing

import me.y9san9.ksm.telegram.routing.base.StateListBase
import me.y9san9.ksm.telegram.state.State
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set

@PipelineDsl
public fun StateRouting.state(block: State.Builder.() -> Unit) {
    val builder = State.Builder()
    builder.block()
    val state = builder.build()
    context[StateListBase.Config.StateList] = context.require(StateListBase.Config.StateList) + state
}
