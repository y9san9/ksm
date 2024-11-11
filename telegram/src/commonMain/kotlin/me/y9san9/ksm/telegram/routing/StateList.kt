package me.y9san9.ksm.telegram.routing

import me.y9san9.ksm.telegram.state.State

public data class StateList(
    public val initial: State,
    public val states: List<State>
) {
    init {
        for (state in states) {
            if (states.count { second -> second.name.string == state.name.string } > 1) {
                error("Duplicate state name '${state.name.string}'")
            }
        }
    }

    public operator fun get(descriptor: StateDescriptor?): State {
        descriptor ?: return initial
        return states.firstOrNull { state -> state.name == descriptor.name } ?: error("Can't find state named '${descriptor.name}'")
    }
}
