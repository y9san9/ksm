package me.y9san9.ksm.telegram.routing

import me.y9san9.ksm.telegram.group.StateGroup
import me.y9san9.ksm.telegram.group.base.StateGroupBase
import me.y9san9.ksm.telegram.state.State
import me.y9san9.ksm.telegram.state.StateRoute
import me.y9san9.ksm.telegram.state.route
import me.y9san9.pipeline.annotation.PipelineDsl
import me.y9san9.pipeline.context.set

@PipelineDsl
public class StateRouting {
    @PipelineDsl
    public var initial: StateRoute? = null
    public var states: MutableList<State> = mutableListOf()

    public fun toStateList(): StateList {
        val initial = initial ?: error("Please setup initial state in routing")
        val initialState = states.firstOrNull { state -> state.route.name == initial.name } ?: error("Can't find initial state named '${initial.name}'")
        return StateList(initialState, states)
    }
}

@PipelineDsl
public fun StateGroup.Builder.routing(block: StateRouting.() -> Unit) {
    val routing = StateRouting()
    routing.block()
    context[StateGroupBase.Config.StateList] = routing.toStateList()
}
