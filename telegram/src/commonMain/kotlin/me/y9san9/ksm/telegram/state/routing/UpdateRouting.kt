package me.y9san9.ksm.telegram.state.routing

import me.y9san9.ksm.telegram.routing.StateList
import me.y9san9.ksm.telegram.state.State
import me.y9san9.ksm.telegram.state.StateName
import me.y9san9.pipeline.annotation.PipelineDsl

@PipelineDsl
public class UpdateRouting {
    @PipelineDsl
    public var initial: StateName? = null
    public var states: MutableList<State> = mutableListOf()

    public fun toStateList(): StateList {
        val initial = initial ?: error("Please setup initial state in routing")
        val initialState = states.firstOrNull { state -> state.name.string == initial.string } ?: error("Can't find initial state named '${initial.string}'")
        return StateList(initialState, states)
    }
}
