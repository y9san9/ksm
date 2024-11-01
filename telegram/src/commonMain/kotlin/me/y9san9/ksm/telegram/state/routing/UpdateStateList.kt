package me.y9san9.ksm.telegram.state.routing

import me.y9san9.ksm.telegram.state.UpdateState
import me.y9san9.ksm.telegram.state.name

public class UpdateStateList(
    public val initial: UpdateState,
    public val states: List<UpdateState>
) {
    init {
        for (state in states) {
            if (states.count { second -> second.name.string == state.name.string } > 1) {
                error("Duplicate state name '${state.name.string}'")
            }
        }
    }

    public operator fun get(descriptor: StateDescriptor?): UpdateState {
        descriptor ?: return initial
        return states.firstOrNull { state -> state.name.string == descriptor.name } ?: error("Can't find state named '${descriptor.name}'")
    }
}
