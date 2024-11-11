package me.y9san9.ksm.telegram.handler

import me.y9san9.ksm.telegram.TelegramFSM.Plugin.SelectedState
import me.y9san9.ksm.telegram.transition.FSMTransition.Plugin.Continuation
import me.y9san9.ksm.telegram.transition.StateContinuation
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import kotlin.coroutines.resume
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine

public val RunPhase: PipelinePhase = buildPipelinePhase {
    name = "RunPhase"

    runnable {
        val state = context.require(SelectedState)
        val stateHandler = state.handler

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
                suspend { stateHandler.run(subject) }.startCoroutine(continuation)
            }
        }
    }
}

private suspend fun waitForever(): Nothing {
    suspendCoroutine<Nothing> {  }
}
