package me.y9san9.ksm.telegram.routing.base

import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase.GotoCommand
import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase.GotoContinuation
import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase.State
import me.y9san9.ksm.telegram.state.UpdateTransition
import me.y9san9.ksm.telegram.state.base.UpdateStateBase
import me.y9san9.ksm.telegram.state.routing.GotoContinuation
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
        val command = require(GotoCommand)
        if (!command.transition) return@runnable
        context[GotoCommand] = command.copy(transition = false)

        val state = require(State)
        val transition = state.context.require(UpdateStateBase.Transition)

        suspendCoroutine { continuation ->
            val stateContinuation = GotoContinuation { finishSubject ->
                context += finishSubject
                continuation.resume(Unit)
                waitForever()
            }
            val subject = toPipelineContext().build {
                context[GotoContinuation] = stateContinuation
            }
            val scope = UpdateTransition.Scope(subject)
            suspend { transition.run(scope) }.startCoroutine(continuation)
        }
    }
}

private suspend fun waitForever(): Nothing {
    suspendCoroutine<Nothing> {  }
}
