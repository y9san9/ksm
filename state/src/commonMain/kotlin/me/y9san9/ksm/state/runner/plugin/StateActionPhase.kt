package me.y9san9.ksm.state.runner.plugin

import me.y9san9.ksm.state.StateScope
import me.y9san9.ksm.state.runner.plugin.StateRunnerBase.Subject
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.*
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.subject.subject

public val StateActionPhase: PipelinePhase = buildPipelinePhase {
    name = "StateAction"

    runnable {
        val action = subject.require(Subject.StateAction)
        val scope = StateScope.of(context)
        action.run(scope)
    }
}
