package me.y9san9.ksm.telegram.handler.base

import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
import me.y9san9.ksm.telegram.state.routing.StateDescriptor
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.ksm.telegram.state.name
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

public val RoutePhase: PipelinePhase = buildPipelinePhase {
    name = "RoutePhase"

    runnable {
        val states = context.require(Subject.StateList)
        val descriptor = context[Subject.Descriptor]

        val state = states[descriptor]
        context[Subject.State] = state

        if (descriptor == null) {
            context[Subject.Descriptor] = StateDescriptor(state.name.string, StateData.Map.Empty, StateData.Null)
        }
    }
}
