package me.y9san9.ksm.telegram.handler.base

import me.y9san9.ksm.telegram.handler.base.UpdateHandlerBase.State
import me.y9san9.ksm.telegram.state.StateContinuation
import me.y9san9.ksm.telegram.state.routing.GotoCommand
import me.y9san9.ksm.telegram.state.UpdateHandler
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.Continuation
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.Descriptor
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.GotoCommand
import me.y9san9.ksm.telegram.state.base.UpdateStateBase.Handler
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import kotlin.coroutines.resume
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine

public val RunPhase: PipelinePhase = buildPipelinePhase {
    name = "RunPhase"

    runnable {
        val state = context.require(State)
        val stateHandler = state.context[Handler]

        if (stateHandler != null) {
            suspendCoroutine { continuation ->
                val stateContinuation = StateContinuation { finishSubject ->
                    context += finishSubject
                    continuation.resume(Unit)
                    waitForever()
                }
                val subject = build {
                    context[Continuation] = stateContinuation
                }
                val scope = UpdateHandler.Scope(subject)
                suspend { stateHandler.run(scope) }.startCoroutine(continuation)
            }
        }

        if (GotoCommand !in context) {
            val descriptor = context.require(Descriptor)
            context[GotoCommand] = GotoCommand(descriptor, transition = false)
        }
    }
}

private suspend fun waitForever(): Nothing {
    suspendCoroutine<Nothing> {  }
}
