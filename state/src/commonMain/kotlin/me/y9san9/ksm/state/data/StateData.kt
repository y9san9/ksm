package me.y9san9.ksm.state.data

public sealed interface StateData {
    // todo: Add all primitive types
    public data object Null : StateData
    public data class String(val string: kotlin.String) : StateData
    public data class List(val list: kotlin.collections.List<StateData>) : StateData {
        public companion object {
            public val Empty: List = List(emptyList())
        }
    }
    public data class Map(val map: kotlin.collections.Map<kotlin.String, StateData>) : StateData {
        public operator fun plus(map: Map): Map {
            return Map(map = this.map + map.map)
        }

        public companion object {
            public val Empty: Map = Map(emptyMap())
        }
    }
}
