package me.y9san9.ksm.router.plugin

import me.y9san9.ksm.state.name
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.*
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.subject.setSubject
import me.y9san9.pipeline.subject.subject

public val RouteByNamePhase: PipelinePhase = buildPipelinePhase {
    name = "RouteByName"

    runnable {
        val states = subject.require(StateRouterBase.Subject.StateList)
        val descriptor = subject[StateRouterBase.Subject.Descriptor]
        val name = descriptor?.id

        // todo: refactor to subject for StateRunner
        if (descriptor == null) {
            setSubject(StateRouterBase.Subject.State, states.initial)
            return@runnable
        }

        for (state in states.list) {
            if (state.name == name) {
                setSubject(StateRouterBase.Subject.State, state)
                return@runnable
            }
        }

        error("Can't find state named '$name'")
    }
}
