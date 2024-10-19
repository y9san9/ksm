package me.y9san9.ksm.routing

import me.y9san9.ksm.router.StateList
import me.y9san9.ksm.state.State
import me.y9san9.ksm.state.buildState
import me.y9san9.ksm.state.name

public interface StateRouting {
    public var initial: String?
    public val states: MutableList<State>

    public companion object {
        public fun of(base: StateList? = null): StateRouting {
            val initial = base?.initial?.name
            val states = base?.list.orEmpty().toMutableList()

            return object : StateRouting {
                override var initial: String? = initial
                override val states: MutableList<State> = states
                override fun toString() = "Routing(list=$states)"
            }
        }
    }
}

public inline fun StateRouting.state(block: State.Builder.() -> Unit) {
    states += buildState(block = block)
}

public fun StateRouting.build(): StateList {
    if (initial == null) error("Please setup initial state in routing")
    return StateList(
        initial = states.find { state -> state.name == initial } ?: error("Can't find state named '$initial'"),
        list = states.toList()
    )
}
