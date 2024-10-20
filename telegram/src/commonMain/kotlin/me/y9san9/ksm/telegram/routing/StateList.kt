package me.y9san9.ksm.telegram.routing

import me.y9san9.ksm.telegram.state.State

public class StateList(
    public val initial: State,
    public val list: List<State>
)
