package me.y9san9.ksm.telegram.routing

public data class GotoCommand(
    public val descriptor: StateDescriptor,
    public val transition: Boolean = true
)
