package me.y9san9.ksm.telegram.state.data

import me.y9san9.ksm.telegram.state.base.UpdateStateBase.Descriptor
import me.y9san9.ksm.telegram.state.routing.StateRouter
import me.y9san9.pipeline.context.require

public sealed interface StateData {
    public data object Null : StateData
    public data class String(val string: kotlin.String) : StateData
    public data class Boolean(val boolean: kotlin.Boolean) : StateData
    public data class Float(val string: kotlin.String) : StateData {
        val float: kotlin.Float get() = string.toFloat()
        val double: Double get() = string.toDouble()
    }
    public data class Int(val string: kotlin.String) : StateData {
        val int: kotlin.Int get() = string.toInt()
        val long: Long get() = string.toLong()
    }
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

public val StateRouter.data: StateData
    get() = context.require(Descriptor).data
