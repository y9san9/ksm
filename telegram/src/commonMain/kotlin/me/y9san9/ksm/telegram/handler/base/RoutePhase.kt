package me.y9san9.ksm.telegram.handler.base

import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
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
        val name = descriptor?.id

        if (descriptor == null) {
            context[Subject.State] = states.initial
            return@runnable
        }

        for (state in states.list) {
            if (state.name == name) {
                context[Subject.State] = state
                return@runnable
            }
        }

        error("Can't find state named '$name'")
    }
}
