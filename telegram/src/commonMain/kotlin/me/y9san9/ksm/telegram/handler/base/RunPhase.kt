package me.y9san9.ksm.telegram.handler.base

import me.y9san9.ksm.telegram.handler.GotoCommand
import me.y9san9.ksm.telegram.handler.base.TelegramUpdateHandlerBase.Subject
import me.y9san9.ksm.telegram.state.UpdateHandler
import me.y9san9.ksm.telegram.state.base.UpdateStateBase
import me.y9san9.ksm.telegram.state.continuation.UpdateStateContinuation
import me.y9san9.pipeline.context.*
import me.y9san9.pipeline.phase.PipelinePhase
import me.y9san9.pipeline.phase.buildPipelinePhase
import me.y9san9.pipeline.phase.name
import me.y9san9.pipeline.phase.runnable
import me.y9san9.pipeline.subject
import kotlin.coroutines.resume
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine

public val RunPhase: PipelinePhase = buildPipelinePhase {
    name = "RunPhase"

    runnable {
        val state = context.require(Subject.State)
        val stateHandler = state.context[UpdateStateBase.Config.Handler]

        if (stateHandler != null) {
            suspendCoroutine { continuation ->
                val stateContinuation = UpdateStateContinuation { finishSubject ->
                    context += finishSubject
                    continuation.resume(Unit)
                    waitForever()
                }
                val subject = state.context.subject.build {
                    context[Subject.Continuation] = stateContinuation
                }
                val scope = UpdateHandler.Scope(context = toPipelineContext() + subject)
                suspend { stateHandler.run(scope) }.startCoroutine(continuation)
            }
        }

        if (Subject.GotoCommand !in context) {
            val descriptor = context.require(Subject.Descriptor)
            context[Subject.GotoCommand] = GotoCommand(descriptor, transition = false)
        }
    }
}

private suspend fun waitForever(): Nothing {
    suspendCoroutine<Nothing> {  }
}
