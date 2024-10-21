package me.y9san9.ksm.telegram.routing

import me.y9san9.ksm.telegram.routing.base.StateListBase.Config
import me.y9san9.ksm.telegram.state.State
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.ksm.telegram.state.name
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require

public class StateList(public val context: PipelineContext) {
    public operator fun get(descriptor: StateDescriptor?): State {
        val list = context.require(Config.StateList)

        if (descriptor == null) {
            val initial = context.require(Config.Initial) { "Please setup `initial` for state list" }
            return get(StateDescriptor(initial, StateData.Map.Empty, StateData.Null))
        }

        for (state in list) {
            if (state.name == descriptor.name) {
                return state
            }
        }

        error("Can't find state named '${descriptor.name}'")
    }
}
