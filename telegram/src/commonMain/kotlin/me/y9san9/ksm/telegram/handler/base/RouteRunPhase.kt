package me.y9san9.ksm.telegram.handler.base

import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
import me.y9san9.ksm.telegram.routing.StateDescriptor
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.ksm.telegram.state.name
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

public val RouteRunPhase: PipelinePhase = buildPipelinePhase {
    name = "RouteRunPhase"

    runnable {
        val states = context.require(Subject.StateList)
        val descriptor = context[Subject.RestoredDescriptor]

        val state = states[descriptor]
        context[Subject.RestoredState] = state

        if (descriptor == null) {
            context[Subject.RestoredDescriptor] = StateDescriptor(state.name, StateData.Map.Empty, StateData.Null)
        }

        context[Subject.StateData] = descriptor?.data ?: StateData.Null
    }
}
