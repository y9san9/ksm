package me.y9san9.ksm.fsm.plugin

import me.y9san9.ksm.fsm.FSM
import me.y9san9.ksm.fsm.FSMNavigator
import me.y9san9.ksm.fsm.FSMRunner
import me.y9san9.ksm.fsm.navigation.build
import me.y9san9.ksm.state.State
import me.y9san9.ksm.state.build
import me.y9san9.ksm.state.docs.docs
import me.y9san9.ksm.state.runner.build
import me.y9san9.ksm.state.runner.run
import me.y9san9.pipeline.context.build
import me.y9san9.pipeline.context.plus
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.context.set
import me.y9san9.pipeline.docs.*
import me.y9san9.pipeline.phase.*
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.subject.subject

public val FSMRunPhase: PipelinePhase = buildPipelinePhase {
    name = "FSMRunPhase"

    describe {
        from(context.require(FSMRunner).docs())
        contextDescription = listOf(
            "FSMRunner - runner used to run states",
            "FSMNavigator - navigator that used when StateScope.navigate is invoked"
        )
        subjectDescription = listOf("Subject is expected to be of type State")
    }

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
