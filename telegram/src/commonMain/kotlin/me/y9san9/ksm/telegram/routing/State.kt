package me.y9san9.ksm.telegram.routing

import me.y9san9.ksm.telegram.state.State
import me.y9san9.ksm.telegram.state.StateRoute
import me.y9san9.ksm.telegram.state.base.StateBase
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.set

@PipelineDsl
public fun StateRouting.state(route: StateRoute, block: State.Builder.() -> Unit) {
    val builder = State.Builder()
    builder.context[StateBase.Config.Route] = route
    builder.block()
    states += builder.build()
}
