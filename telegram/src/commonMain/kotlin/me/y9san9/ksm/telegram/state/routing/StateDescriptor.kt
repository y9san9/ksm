package me.y9san9.ksm.telegram.state.routing

import me.y9san9.ksm.telegram.state.data.StateData

public data class StateDescriptor(
    public val name: String,
    public val parameters: StateData.Map,
    public val data: StateData
) {
    public companion object {
        public const val NAME: String = "me.y9san9.ksm.router.descriptor.NAME"
        public const val PARAMETERS: String = "me.y9san9.ksm.router.descriptor.PARAMETERS"
        public const val DATA: String = "me.y9san9.ksm.router.descriptor.DATA"

        public fun decode(from: StateData.Map): StateDescriptor {
            val id = from.map.getValue(NAME) as StateData.String
            val parameters = from.map.getValue(PARAMETERS) as StateData.Map
            val data = from.map.getValue(DATA)
            return StateDescriptor(id.string, parameters, data)
        }

        public fun encode(from: StateDescriptor): StateData.Map {
            val map = mapOf(
                NAME to StateData.String(from.name),
                PARAMETERS to from.parameters,
                DATA to from.data
            )
            return StateData.Map(map)
        }
    }
}
