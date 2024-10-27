package me.y9san9.ksm.telegram.handler

import me.y9san9.ksm.telegram.state.routing.StateDescriptor

public data class GotoCommand(
    public val descriptor: StateDescriptor,
    public val transition: Boolean = true
)
