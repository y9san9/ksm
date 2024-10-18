package me.y9san9.ksm.fsm.plugin

import me.y9san9.ksm.fsm.FSMRouter
import me.y9san9.ksm.fsm.FSMStateList
import me.y9san9.ksm.router.StateList
import me.y9san9.ksm.router.StateDescriptor
import me.y9san9.ksm.router.route
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.*
import me.y9san9.pipeline.subject.subject

public val FSMRoutePhase: PipelinePhase = buildPipelinePhase {
    name = "FSMRoute"

    runnable {
        val context = context

        subject {
            this[StateList] = context.require(FSMStateList)
        }

        val router = context.require(FSMRouter)
        val descriptor = StateDescriptor.of(subject)
        subject = router.route(descriptor).context
    }
}
