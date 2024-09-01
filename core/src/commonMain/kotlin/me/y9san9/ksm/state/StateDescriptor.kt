package me.y9san9.ksm.state

public class StateDescriptor(
    public val name: StateName,
    public val parameters: StateData.Map = StateData.Map.Empty
)
