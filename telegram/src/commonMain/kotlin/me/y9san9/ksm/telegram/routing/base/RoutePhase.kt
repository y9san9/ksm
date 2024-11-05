package me.y9san9.ksm.telegram.routing.base

import me.y9san9.ksm.telegram.routing.base.FSMRouterBase.State
import me.y9san9.ksm.telegram.routing.base.FSMRouterBase.StateList
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.Descriptor
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.GotoCommand
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

public val RouterFindByNamePhase: PipelinePhase = buildPipelinePhase {
    name = "RouterFindByNamePhase"

    runnable {
        val states = require(StateList)
        val descriptor = require(GotoCommand).descriptor
        context[State] = states[descriptor]
        context[Descriptor] = descriptor
    }
}
