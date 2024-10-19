package me.y9san9.ksm.router

import me.y9san9.ksm.state.data.StateData

public data class StateDescriptor(
    public val id: String,
    public val parameters: StateData.Map,
    public val data: StateData
) {
    public companion object {
        public const val ID: String = "me.y9san9.ksm.router.descriptor.ID"
        public const val PARAMETERS: String = "me.y9san9.ksm.router.descriptor.PARAMETERS"
        public const val DATA: String = "me.y9san9.ksm.router.descriptor.DATA"

        public fun decode(from: StateData.Map): StateDescriptor {
            val id = from.map.getValue(ID) as StateData.String
            val parameters = from.map.getValue(PARAMETERS) as StateData.Map
            val data = from.map.getValue(DATA)
            return StateDescriptor(id.string, parameters, data)
        }

        public fun encode(from: StateDescriptor): StateData.Map {
            val map = mapOf(
                ID to StateData.String(from.id),
                PARAMETERS to from.parameters,
                DATA to from.data
            )
            return StateData.Map(map)
        }
    }
}
