package me.y9san9.ksm.telegram.handler.base

import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
import me.y9san9.ksm.telegram.state.data.StateData
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable

public val RouteNavigatePhase: PipelinePhase = buildPipelinePhase {
    name = "RouteNavigatePhase"

    runnable {
        val states = require(Subject.StateList)
        val descriptor = require(Subject.GotoCommand).descriptor
        context[Subject.GotoState] = states[descriptor]
        context[Subject.StateData] = descriptor.data
    }
}
