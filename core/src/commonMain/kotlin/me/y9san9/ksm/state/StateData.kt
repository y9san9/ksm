package me.y9san9.ksm.state

public sealed interface StateData {
    // todo: Add all primitive types
    public data class String(val string: kotlin.String) : StateData
    public data class List(val list: kotlin.collections.List<StateData>) : StateData {
        public companion object {
            public val Empty: List = List(emptyList())
        }
    }
    public data class Map(val map: kotlin.collections.Map<kotlin.String, StateData>) : StateData {
        public companion object {
            public val Empty: Map = Map(emptyMap())
        }
    }
}
