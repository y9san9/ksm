package me.y9san9.ksm.state.runner.plugin

import me.y9san9.ksm.state.StateAction
import me.y9san9.ksm.state.StateScope
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.docs.describe
import me.y9san9.pipeline.docs.description
import me.y9san9.pipeline.docs.subjectDescription
import me.y9san9.pipeline.phase.*
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.subject.subject

public val StateActionPhase: PipelinePhase = buildPipelinePhase {
    name = "StateAction"

    describe {
        description = "Action to perform when State is ready. " +
            "Every phase before is considered as configuration, " +
            "everything after is considered as dispose."

        subjectDescription = listOf(
            "StateAction - required. Action to be run"
        )
    }

    runnable {
        val action = subject.require(StateAction)
        val scope = StateScope.of(context)
        action.run(scope)
    }
}
