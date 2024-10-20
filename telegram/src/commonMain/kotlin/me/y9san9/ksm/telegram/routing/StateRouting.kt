package me.y9san9.ksm.telegram.routing

import me.y9san9.ksm.telegram.state.State
import me.y9san9.ksm.telegram.state.name

public class StateRouting(
    public var initial: String? = null,
    public val states: MutableList<State> = mutableListOf()
)

public fun StateRouting.toStateList(): StateList {
    if (initial == null) error("Please setup initial state in routing")
    return StateList(
        initial = states.find { state -> state.name == initial } ?: error("Can't find state named '$initial'"),
        list = states.toList()
    )
}

public inline fun buildStateList(
    from: StateList? = null,
    block: StateRouting.() -> Unit
): StateList {
    val routing = when(from) {
        null -> StateRouting()
        else -> StateRouting(from.initial.name, from.list.toMutableList())
    }
    routing.block()
    return routing.toStateList()
}

public inline fun StateList?.build(block: StateRouting.() -> Unit): StateList {
    return buildStateList(from = this, block = block)
}
