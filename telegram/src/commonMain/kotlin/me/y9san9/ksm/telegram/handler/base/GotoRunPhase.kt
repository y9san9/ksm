package me.y9san9.ksm.telegram.handler.base

import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
import me.y9san9.ksm.telegram.state.StateHandler
import me.y9san9.ksm.telegram.state.base.StateBase
import me.y9san9.ksm.telegram.state.continuation.StateContinuation
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.subject
import kotlin.coroutines.resume
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine

public val GotoRunPhase: PipelinePhase = buildPipelinePhase {
    name = "GotoRunPhase"

    runnable {
        val command = require(Subject.GotoCommand)
        if (!command.transition) return@runnable
        context[Subject.GotoCommand] = command.copy(transition = false)

        val state = require(Subject.State)
        val transition = state.context.require(StateBase.Config.Transition)

        suspendCoroutine { continuation ->
            val stateContinuation = StateContinuation { finishSubject ->
                context += finishSubject
                continuation.resume(Unit)
                waitForever()
            }
            val subject = state.context.subject.build {
                context[Subject.Continuation] = stateContinuation
            } + toPipelineContext()
            val scope = StateHandler.Scope(subject)
            suspend { transition.run(scope) }.startCoroutine(continuation)
        }
    }
}

private suspend fun waitForever(): Nothing {
    suspendCoroutine<Nothing> {  }
}
