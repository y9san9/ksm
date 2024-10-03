package me.y9san9.ksm.router.plugin

import me.y9san9.ksm.router.descriptor.StateDescriptorName
import me.y9san9.ksm.router.StateList
import me.y9san9.ksm.state.name
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.docs.*
import me.y9san9.pipeline.docs.description
import me.y9san9.pipeline.phase.*
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.subject.subject

public val RouteByNamePhase: PipelinePhase = buildPipelinePhase {
    name = "RouteByName"

    describe {
        description = "Selects specific State from StateList by name. Returned subject is PipelineContext of the selected State"

        subjectDescription = listOf(
            "StateDescriptorName: String? - state name to launch or null to launch initial",
            "StateList: StateList - list of states to select from"
        )
    }

    runnable {
        val name = subject[StateDescriptorName]
        val states = subject.require(StateList) { "StateDescriptor must contain StateList to select from" }

        if (name == null) {
            subject = states.initial.context
            return@runnable
        }

        for (state in states.list) {
            if (state.name == name) {
                subject = state.context
                return@runnable
            }
        }

        error("Can't find state named '$name'")
    }
}
