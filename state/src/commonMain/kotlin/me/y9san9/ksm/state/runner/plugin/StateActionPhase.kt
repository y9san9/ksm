package me.y9san9.ksm.state.runner.plugin

import me.y9san9.ksm.state.StateHandler
import me.y9san9.ksm.state.continuation.StateContinuation
import me.y9san9.ksm.state.runner.plugin.StateRunnerBase.Subject
import me.y9san9.pipeline.context.plus
import me.y9san9.pipeline.context.require
import me.y9san9.pipeline.phase.*
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.subject.setSubject
import me.y9san9.pipeline.subject.subject
import kotlin.coroutines.resume
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine

public val StateActionPhase: PipelinePhase = buildPipelinePhase {
    name = "StateActionPhase"

    runnable {
        val action = subject.require(Subject.StateAction)

        suspendCoroutine { continuation ->
            val stateContinuation = StateContinuation { finishContext ->
                subject += finishContext
                continuation.resume(Unit)
                waitForever()
            }
            setSubject(Subject.StateContinuation, stateContinuation)
            val scope = StateHandler.Scope(context)
            suspend { action.run(scope) }.startCoroutine(continuation)
        }
    }
}

private suspend fun waitForever(): Nothing {
    suspendCoroutine<Nothing> {  }
}
