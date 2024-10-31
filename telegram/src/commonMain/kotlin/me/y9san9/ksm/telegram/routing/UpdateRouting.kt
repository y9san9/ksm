package me.y9san9.ksm.telegram.routing

import me.y9san9.ksm.telegram.state.UpdateState
import me.y9san9.ksm.telegram.state.StateName
import me.y9san9.ksm.telegram.state.name
import me.y9san9.pipeline.annotation.PipelineDsl

// todo: remove inheritance
@PipelineDsl
public open class UpdateRouting {
    @PipelineDsl
    public var initial: StateName? = null
    public var states: MutableList<UpdateState> = mutableListOf()

    public fun toStateList(): UpdateStateList {
        val initial = initial ?: error("Please setup initial state in routing")
        val initialState = states.firstOrNull { state -> state.name.string == initial.string } ?: error("Can't find initial state named '${initial.string}'")
        return UpdateStateList(initialState, states)
    }
}
