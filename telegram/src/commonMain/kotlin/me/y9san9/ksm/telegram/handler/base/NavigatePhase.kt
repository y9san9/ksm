package me.y9san9.ksm.telegram.handler.base

import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
import me.y9san9.ksm.telegram.state.StateHandler
import me.y9san9.ksm.telegram.state.base.StateBase
import me.y9san9.pipeline.context.plus
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.subject

public val NavigatePhase: PipelinePhase = buildPipelinePhase {
    name = "NavigatePhase"

    runnable {
        val state = require(Subject.State)
        val transition = state.context.require(StateBase.Config.Transition)
        val scope = StateHandler.Scope(context = toPipelineContext() + state.context.subject)
        transition.run(scope)
    }
}
