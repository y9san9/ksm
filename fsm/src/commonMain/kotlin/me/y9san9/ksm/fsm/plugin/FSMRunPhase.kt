package me.y9san9.ksm.fsm.plugin

import me.y9san9.ksm.fsm.FSM
import me.y9san9.ksm.fsm.FSMRunner
import me.y9san9.ksm.state.State
import me.y9san9.ksm.state.runner.run
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.phase.*
import me.y9san9.pipeline.subject.subject

public val FSMRunPhase: PipelinePhase = buildPipelinePhase {
    name = "FSMRunPhase"

    runnable {
        val pipelineContext = context

        subject {
            // Put FSM into subject
            this[FSM] = FSM.of(pipelineContext)
        }

        val runner = context.require(FSMRunner)
        val state = State.of(subject)
        runner.run(state)
    }
}
