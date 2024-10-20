package me.y9san9.ksm.telegram.routing

import me.y9san9.ksm.telegram.state.State
import me.y9san9.ksm.telegram.state.name

public class StateList(
    public val initial: State,
    public val list: List<State>
) {
    public operator fun get(descriptor: StateDescriptor?): State {
        descriptor ?: return initial

        for (state in list) {
            if (state.name == descriptor.name) {
                return state
            }
        }

        error("Can't find state named '${descriptor.name}'")
    }
}
