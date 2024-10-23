package me.y9san9.ksm.telegram.routing

import me.y9san9.ksm.telegram.state.State
import me.y9san9.ksm.telegram.state.route

public class StateList(
    public val initial: State,
    public val states: List<State>
) {
    init {
        for (state in states) {
            if (states.count { second -> second.route.name == state.route.name } > 1) {
                error("Duplicate state name '${state.route.name}'")
            }
        }
    }

    public operator fun get(descriptor: StateDescriptor?): State {
        descriptor ?: return initial
        return states.firstOrNull { state -> state.route.name == descriptor.name } ?: error("Can't find state named '${descriptor.name}'")
    }
}
