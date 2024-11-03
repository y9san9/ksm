package me.y9san9.ksm.telegram.handler.base

import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase.Descriptor
import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase.State
import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase.StateList
import me.y9san9.ksm.telegram.state.routing.StateDescriptor
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.ksm.telegram.state.name
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

public val FindByNamePhase: PipelinePhase = buildPipelinePhase {
    name = "FindByNamePhase"

    runnable {
        val states = context.require(StateList)
        val descriptor = context[Descriptor]

        val state = states[descriptor]
        context[State] = state

        if (descriptor == null) {
            context[Descriptor] = StateDescriptor(state.name.string, StateData.Map.Empty, StateData.Null)
        }
    }
}
