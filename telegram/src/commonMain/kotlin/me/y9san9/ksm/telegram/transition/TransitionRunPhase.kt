package me.y9san9.ksm.telegram.transition

import me.y9san9.ksm.telegram.TelegramFSM.Plugin.SelectedState
import me.y9san9.ksm.telegram.transition.FSMTransition.Plugin.Continuation
import me.y9san9.ksm.telegram.transition.FSMTransition.Plugin.GotoCommand
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import kotlin.coroutines.resume
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine

public val TransitionRunPhase: PipelinePhase = buildPipelinePhase {
    name = "TransitionRunPhase"

    runnable {
        val command = require(GotoCommand)
        if (!command.transition) return@runnable
        context[GotoCommand] = command.copy(transition = false)

        val state = require(SelectedState)
        val transition = state.transition ?: return@runnable

        suspendCoroutine { continuation ->
            val stateContinuation = StateContinuation { finishSubject ->
                context += finishSubject
                continuation.resume(Unit)
                waitForever()
            }
            val subject = build {
                context[Continuation] = stateContinuation
            }
            suspend { transition.run(subject) }.startCoroutine(continuation)
        }
    }
}

private suspend fun waitForever(): Nothing {
    suspendCoroutine<Nothing> {  }
}
